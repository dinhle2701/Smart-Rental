package com.project.SmartRental.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;

@Configuration
public class SwaggerConfig {

    @Bean
    public GlobalOpenApiCustomizer globalSorter() {
        return (OpenAPI openApi) -> {
            // 1) Sắp xếp METHODS trong từng path: GET(all) → GET(id) → POST → PUT → PATCH → DELETE
            openApi.getPaths().forEach((path, pathItem) -> reorderOperations(path, pathItem));

            // 2) Sắp xếp PATHS: base path trước param path (vd: /vehicle trước /vehicle/{id})
            List<Map.Entry<String, PathItem>> entries = new ArrayList<>(openApi.getPaths().entrySet());
            entries.sort((a, b) -> comparePaths(a.getKey(), b.getKey()));

            Paths sortedPaths = new Paths();
            for (Map.Entry<String, PathItem> e : entries) {
                sortedPaths.addPathItem(e.getKey(), e.getValue());
            }
            openApi.setPaths(sortedPaths);
        };
    }

    // ------- Helpers -------
    /**
     * Ưu tiên: GET(all) → GET(id) → POST → PUT → PATCH → DELETE
     */
    private void reorderOperations(String path, PathItem pathItem) {
        Map<PathItem.HttpMethod, Operation> original = pathItem.readOperationsMap();
        LinkedHashMap<PathItem.HttpMethod, Operation> ordered = new LinkedHashMap<>();

        boolean byId = isIdPath(path);

        // GET(all)
        if (!byId && original.containsKey(PathItem.HttpMethod.GET)) {
            ordered.put(PathItem.HttpMethod.GET, original.get(PathItem.HttpMethod.GET));
        }

        // GET(id)
        if (byId && original.containsKey(PathItem.HttpMethod.GET)) {
            ordered.put(PathItem.HttpMethod.GET, original.get(PathItem.HttpMethod.GET));
        }

        // POST → PUT → PATCH → DELETE
        if (original.containsKey(PathItem.HttpMethod.POST)) {
            ordered.put(PathItem.HttpMethod.POST, original.get(PathItem.HttpMethod.POST));
        }
        if (original.containsKey(PathItem.HttpMethod.PUT)) {
            ordered.put(PathItem.HttpMethod.PUT, original.get(PathItem.HttpMethod.PUT));
        }
        if (original.containsKey(PathItem.HttpMethod.PATCH)) {
            ordered.put(PathItem.HttpMethod.PATCH, original.get(PathItem.HttpMethod.PATCH));
        }
        if (original.containsKey(PathItem.HttpMethod.DELETE)) {
            ordered.put(PathItem.HttpMethod.DELETE, original.get(PathItem.HttpMethod.DELETE));
        }

        // các method khác (HEAD/OPTIONS/TRACE...) đẩy cuối
        original.forEach(ordered::putIfAbsent);

        ordered.forEach(pathItem::operation);
    }

    /**
     * So sánh để path base đứng trước path có tham số: /x/y < /x/{id}
     */
    private int comparePaths(String p1, String p2) {
        // cùng “nhóm” base?
        String base1 = baseOf(p1);
        String base2 = baseOf(p2);
        int cmpBase = base1.compareTo(base2);
        if (cmpBase != 0) {
            return cmpBase;
        }

        // cùng base: non-param trước param
        boolean id1 = isIdPath(p1);
        boolean id2 = isIdPath(p2);
        if (id1 == id2) {
            return p1.compareTo(p2); // tie-breaker theo chữ cái

                }return id1 ? 1 : -1;
    }

    /**
     * Lấy phần base trước tham số: /a/b/{id} -> /a/b
     */
    private String baseOf(String path) {
        if (path == null) {
            return "";
        }
        int i = path.indexOf("/{");
        return i >= 0 ? path.substring(0, i) : path;
    }

    private boolean isIdPath(String path) {
        return path != null && path.contains("{") && path.contains("}");
    }
}
