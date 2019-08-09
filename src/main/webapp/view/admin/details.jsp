<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <fmt:message key="text.username"/>
    ${client.username}
    <form method="post">
        <fmt:message key="text.coupon"/>
        <input type="number" pattern="0.00" min="0.01" max="0.99" step=".01" name="percent">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button name="command" value="createCoupon" type="submit" class="btn btn-primary">
            <fmt:message key="text.create"/></button>
    </form>
</div>