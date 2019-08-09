<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <div class="form-group">
                    <fmt:message key="text.cost"/>
                    ${order.cost}$
                </div>
                <div class="form-group">
                    <fmt:message key="text.distance"/>
                    ${order.distance}
                </div>
                <div class="form-group">
                    <fmt:message key="text.drivingTime"/>
                    ${order.drivingTime}
                </div>
                <div class="form-group">
                    <fmt:message key="text.waitingTime"/>
                    ${order.waitingTime}
                </div>
                <div class="form-group">
                    <fmt:message key="text.orderDate"/>
                    ${order.orderDate}
                </div>
                <div class="form-group">
                    <fmt:message key="text.type"/>
                    ${order.type}
                </div>
                <div class="form-group">
                    <fmt:message key="text.taxi"/>
                    ${order.taxi.carNumber}

                </div>
                <div class="form-group">
                    <fmt:message key="text.start"/>
                    ${order.startStreet}, ${order.startHouse}

                </div>
                <div class="form-group">
                    <fmt:message key="text.end"/>
                    ${order.endStreet}, ${order.endHouse}
                </div>
                <div class="form-group">
                    <fmt:message key="text.status"/>
                    ${order.status}
                </div>
                <div class="form-group mt-3">
                    <form method="post">
                        <c:if test="${order.status.name() == 'Active'}">
                            <button name="command" value="confirmOrder" type="submit" class="btn btn-primary">
                                <fmt:message key="text.confirm"/></button>
                        </c:if>
                        <button name="command" value="deleteOrder" type="submit" class="btn btn-primary">
                            <fmt:message key="text.delete"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
