<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.aina.spring_mvc.model.PlateauNonDisponible"%>
<%@page import="com.aina.spring_mvc.model.Plateau"%>
<%@page import="java.util.Set"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<%
    Plateau platt=(Plateau)request.getAttribute("plateau");
    List<PlateauNonDisponible> vb=new ArrayList(platt.getNondisponible());
%>
<div class="col-md-6 offset-md-2">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">
                    <form th:action="@{/Plateau/indisponible}" th:object="${model}"  method="POST">
                        Date Indisponible: <input type="date" class="form-control" name="dates" th:field="*{dates}"><br>
                        Observation: <textarea name="observation" cols="25" class="form-control" rows="10" th:field="*{observation}"></textarea><br>          
                        <button class="btn btn-primary">Inserer</button>
                    </form>                    
                </div>
                <div class="col-sm-6">
                    <small class="text-light fw-semibold">Date indissponible</small>
                    <div class="demo-inline-spacing mt-3">
                        <ul class="list-group">
                            <% for(int i=0;i<vb.size();i++){ 
                                %> 
                                <li class="list-group-item d-flex align-items-center"><% out.println(vb.get(i).getDates()); %>:&nbsp&nbsp<% out.println(vb.get(i).getObservation()); %></li>
                            <% } %>
                        </ul>
                    </div>      
                </div>
            </div>
        </div>
    </div>        
</div>
<%@ include file="../../footer.jsp" %>