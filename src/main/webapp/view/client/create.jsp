<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <div class="form-group mt-3">
                    <form method="post">

                        <fmt:message key="text.type"/>
                        <select class="custom-select" name="type">
                            <c:forEach var="type" items="${types}">
                                <option value="${type}">${type}</option>
                            </c:forEach>
                        </select>

                        <fmt:message key="text.start"/>
                        <select class="custom-select" name="startStreet">
                            <c:forEach var="street" items="${streets}">
                                <option value=${street}>${street}</option>
                            </c:forEach>
                        </select>
                        <div class="input-default-wrapper mt-3">
                            <input class="input-default-js" name="startHouse" type="number" value="1" pattern="1" max="9" min="1"/>
                        </div>

                        <fmt:message key="text.end"/>
                        <select class="custom-select" name="endStreet">
                            <c:forEach var="street" items="${streets}">
                                <option value=${street}>${street}</option>
                            </c:forEach>
                        </select>
                        <div class="input-default-wrapper mt-3">
                            <input class="input-default-js" name="endHouse" type="number" value="1" pattern="1" max="9" min="1"/>
                        </div>

                        <fmt:message key="text.coupon"/>
                        <select class="custom-select" name="coupon">
                            <option value="0"><fmt:message key="text.none"/></option>
                            <c:forEach var="coupon" items="${coupons}">
                                <option value="${coupon.id}">${coupon.discountPercent}</option>
                            </c:forEach>
                        </select>

                        <div class="form-group">
                            <button name="command" value="createOrder" type="submit" class="btn btn-primary">
                                <fmt:message key="text.save"/></button>
                        </div>

                        <div class="info-message-label">
                            <c:if test="${not empty messageLocation}"><fmt:message key="${messageLocation}"/></c:if>
                        </div>
                        <div class="info-message-label">
                            <c:if test="${not empty messageTaxi}"><fmt:message key="${messageTaxi}"/></c:if>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
