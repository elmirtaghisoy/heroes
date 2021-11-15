package az.netx.heroes.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class SearchUtil {

    public static String postSearchPathBuilder(HttpServletRequest request) {
        String link = "";
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String header = request.getParameter("header");
        String category = request.getParameter("category");

        if (Objects.nonNull(category)) {
            link += "&category=" + category;
        }
        if (Objects.nonNull(from)) {
            link += "&from=" + from;
        }
        if (Objects.nonNull(to)) {
            link += "&to=" + to;
        }
        if (Objects.nonNull(header)) {
            link += "&header=" + header;
        }
        if (Objects.nonNull(category)) {
            link += "&category=" + category;
        }
        return link;
    }

    public static String messageSearchPathBuilder(HttpServletRequest request) {
        String link = "";
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String readDateFrom = request.getParameter("readDateFrom");
        String receivedDateFrom = request.getParameter("receivedDateFrom");
        String readDateTo = request.getParameter("readDateTo");
        String receivedDateTo = request.getParameter("receivedDateTo");

        if (Objects.nonNull(email)) {
            link += "&email=" + email;
        }
        if (Objects.nonNull(status)) {
            link += "&status=" + status;
        }
        if (Objects.nonNull(readDateFrom)) {
            link += "&readDateFrom=" + readDateFrom;
        }
        if (Objects.nonNull(receivedDateFrom)) {
            link += "&receivedDateFrom=" + receivedDateFrom;
        }
        if (Objects.nonNull(readDateTo)) {
            link += "&readDateTo=" + readDateTo;
        }
        if (Objects.nonNull(receivedDateTo)) {
            link += "&receivedDateTo=" + receivedDateTo;
        }

        return link;
    }

    public static String martyredSearchPathBuilder(HttpServletRequest request) {
        String link = "";
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birthDate = request.getParameter("birthDate");
        String fatherName = request.getParameter("fatherName");
        String rank = request.getParameter("rank");

        if (Objects.nonNull(name)) {
            link += "&name=" + name;
        }
        if (Objects.nonNull(surname)) {
            link += "&surname=" + surname;
        }
        if (Objects.nonNull(birthDate)) {
            link += "&birthDate=" + birthDate;
        }
        if (Objects.nonNull(fatherName)) {
            link += "&fatherName=" + fatherName;
        }
        if (Objects.nonNull(rank)) {
            link += "&rank=" + rank;
        }

        return link;
    }

    public static String heroSearchPathBuilder(HttpServletRequest request) {
        String link = "";
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birthDate = request.getParameter("birthDate");
        String fatherName = request.getParameter("fatherName");
        String rank = request.getParameter("rank");

        if (Objects.nonNull(name)) {
            link += "&name=" + name;
        }
        if (Objects.nonNull(surname)) {
            link += "&surname=" + surname;
        }
        if (Objects.nonNull(birthDate)) {
            link += "&birthDate=" + birthDate;
        }
        if (Objects.nonNull(fatherName)) {
            link += "&fatherName=" + fatherName;
        }
        if (Objects.nonNull(rank)) {
            link += "&rank=" + rank;
        }

        return link;
    }
}

