<%@page import="java.util.Set"%>
<%@page import="com.aina.spring_mvc.model.ActeurNonDisponible"%>
<%@page import="com.aina.spring_mvc.model.Acteur"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<%
    List<Acteur> acteur=(List<Acteur>)request.getAttribute("liste");
%>
<div class="col-md-6 offset-md-2">
    <div class="card mb-6">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">
                    <form th:action="@{/Acteur/inserer}" th:object="${acteur}"  method="POST">
                        Nom: <input type="text" class="form-control" name="nom" th:field="*{nom}"><br>
                        <button class="btn btn-primary">Inserer</button>
                    </form>                    
                </div>
                <div class="col-sm-6">
                    <small class="text-light fw-semibold">Liste Acteur</small>
                    <div class="demo-inline-spacing">
                        <ul class="list-group">
                            <% for(int i=0;i<acteur.size();i++){ %> 
                            <li class="list-group-item align-items-center">
                                <div class="row">
                                    <div class="col-sm-8">                                                        
                                        <b><% out.println(acteur.get(i).getNom()); %></b>
                                    </div>
                                    <div class="col-sm-4">
                                        <a href="${pageContext.request.contextPath}/Acteur/indisponible?id=<%=acteur.get(i).getId() %>">Details</a>
                                    </div>
                                </div>
                            </li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
            
        </div>
    </div>        
</div>
<%@ include file="../../footer.jsp" %>