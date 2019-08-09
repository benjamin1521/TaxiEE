<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="table-container">
    <div class="btn-group btn-group-justified">
        <a href="${pageContext.request.contextPath}/app/${user.role}/orders?page=1" class="btn btn-info">
            <fmt:message key="text.all"/></a>
        <a href="${pageContext.request.contextPath}/app/${user.role}/orders?page=1&status=Active" class="btn btn-info">
            <fmt:message key="text.active"/></a>
        <a href="${pageContext.request.contextPath}/app/${user.role}/orders?page=1&status=Done"
           class="btn btn-info">
            <fmt:message key="text.done"/></a>
        <a href="${pageContext.request.contextPath}/app/${user.role}/orders?page=1&status=Unavailable"
           class="btn btn-info">
            <fmt:message key="text.unavailable"/></a>
    </div>

    <c:choose>
        <c:when test="${noOrders}">
            <p style="text-align: center; margin-top: 300px;"><fmt:message key="text.noorders"/></p>
        </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="text.cost"/></th>
                    <th><fmt:message key="text.distance"/></th>
                    <th><fmt:message key="text.drivingTime"/></th>
                    <th><fmt:message key="text.waitingTime"/></th>
                    <th><fmt:message key="text.orderDate"/></th>
                    <th><fmt:message key="text.type"/></th>
                    <th><fmt:message key="text.taxi"/></th>
                    <th><fmt:message key="text.start"/></th>
                    <th><fmt:message key="text.end"/></th>
                    <th><fmt:message key="text.status"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${paginationOrders.list}">
                    <tr class="${order.status}">
                        <td>${order.cost}$</td>
                        <td>${order.distance}</td>
                        <td><fmt:formatNumber value="${order.drivingTime}" maxFractionDigits="2"/></td>
                        <td><fmt:formatNumber value="${order.waitingTime}" maxFractionDigits="2"/></td>
                        <td>${order.orderDate}</td>
                        <td>${order.type}</td>
                        <td>${order.taxi.carNumber}</td>
                        <td>${order.startStreet}, ${order.startHouse}</td>
                        <td>${order.endStreet}, ${order.endHouse}</td>
                        <td>${order.status}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/app/${user.role}/details?id=${order.id}">
                                <fmt:message key="text.edit"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="navigation-block">
                <c:out value="${paginationOrders.firstNumber}"/>-<c:out value="${paginationOrders.lastNumber}"/>
                <fmt:message key="text.from"/>
                <c:out value="${paginationOrders.total}"/>
                <a href="${pageContext.request.contextPath}/app/client/orders?page=${param.page - 1}&status=${param.status}"><-</a>
                <a href="${pageContext.request.contextPath}/app/client/orders?page=${param.page + 1}&status=${param.status}">-></a>
            </div>
        </c:otherwise>
    </c:choose>

</div>
