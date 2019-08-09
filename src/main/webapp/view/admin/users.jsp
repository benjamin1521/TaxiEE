<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="table-container">
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="text.username"/></th>
            <th><fmt:message key="text.role"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.role}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/admin/details?id=${user.id}">
                        <fmt:message key="text.edit"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
