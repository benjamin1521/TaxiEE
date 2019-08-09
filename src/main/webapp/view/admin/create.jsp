<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>


<div class="center-block">

    <form method="post">
        <div class="form-group">
            <fmt:message key="text.type"/>
            <select class="custom-select" name="type">
                <c:forEach var="type" items="${types}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </div>
        <fmt:message key="text.number"/>
        <div class="input-default-wrapper mt-3">
            <input type="text" class="input-default-js" name="number">
        </div>

        <fmt:message key="text.driverName"/>
        <div class="input-default-wrapper mt-3">
            <input type="text" class="input-default-js" name="driverName">
        </div>

        <button name="command" value="createTaxi" type="submit" class="btn btn-primary"><fmt:message
                key="text.save"/></button>

    </form>
</div>