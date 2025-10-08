// Ép Swagger UI sắp đúng thứ tự: base path trước param path, rồi method theo GET all → GET id → POST → PUT → PATCH → DELETE
window.onload = function () {
    function baseOf(p) {
        if (!p) return "";
        const i = p.indexOf("/{");
        return i >= 0 ? p.substring(0, i) : p;
    }
    function weight(op) {
        const method = (op.get("method") || "").toUpperCase();
        const path   = op.get("path") || "";
        const base   = baseOf(path);
        const isId   = /{\w+}/.test(path) ? 1 : 0;

        // Thứ tự method mong muốn
        const methodRank =
            method === "GET"  && !isId ? 1 :
                method === "GET"  &&  isId ? 2 :
                    method === "POST"          ? 3 :
                        method === "PUT"           ? 4 :
                            method === "PATCH"         ? 5 :
                                method === "DELETE"        ? 6 : 999;

        return { base, isId, methodRank, path };
    }

    window.ui = SwaggerUIBundle({
        // dùng configUrl chuẩn cho springdoc v2
        configUrl: "/v2/api-docs/swagger-config",
        dom_id: "#swagger-ui",
        deepLinking: true,
        presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
        layout: "BaseLayout",

        operationsSorter: function (a, b) {
            const A = weight(a), B = weight(b);

            // 1) so base path (vd: /api/v1/vehicle trước /api/v1/vehicle/{id})
            if (A.base < B.base) return -1;
            if (A.base > B.base) return 1;

            // 2) base trước param
            if (A.isId !== B.isId) return A.isId - B.isId;

            // 3) method theo rank mong muốn
            if (A.methodRank !== B.methodRank) return A.methodRank - B.methodRank;

            // 4) tie-breaker ổn định
            if (A.path < B.path) return -1;
            if (A.path > B.path) return 1;
            return 0;
        }
    });
};
