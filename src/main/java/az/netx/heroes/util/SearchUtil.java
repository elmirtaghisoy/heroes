package az.netx.heroes.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class SearchUtil {

    public static String postSearchPathBuilder(HttpServletRequest request) {
        String link = "";
        String header = request.getParameter("header");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String category = request.getParameter("category");

        if (Objects.nonNull(header)) {
            link += "&header=" + header;
        }
        if (Objects.nonNull(category)) {
            link += "&category=" + category;
        }
        if (Objects.nonNull(from)) {
            link += "&from=" + from;
        }
        if (Objects.nonNull(to)) {
            link += "&to=" + to;
        }
        return link;
    }
}
