<%@page import="com.aina.spring_mvc.model.Plateau"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<% 
    List<Plateau> plateau=(List<Plateau>) request.getAttribute("plateau");
%>
<div class="col-md-6 offset-md-3">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">                    
                    <form th:action="@{/Plateau/inserer}" th:object="${objet}"  method="POST"   >
                        Nom: <input type="text" class="form-control" name="nom" th:field="*{nom}"><br>
                        <button class="btn btn-primary">Inserer</button>
                    </form>
                </div>
                <div class="col-sm-6">  
                    <small class="text-light fw-semibold">Liste Plateau</small>
                    <div class="demo-inline-spacing mt-3">
                        <ul class="list-group">
                            <% for(int i=0;i<plateau.size();i++){ %> 
                                <li class="list-group-item  align-items-center">
                                    <div class="row">
                                        <div class="col-sm-8">                                                        
                                            <% out.println(plateau.get(i).getNom()); %>
                                        </div>
                                        <div class="col-sm-4">
                                            <a href="${pageContext.request.contextPath}/Plateau/indisponible?id=<%=plateau.get(i).getId() %>"">Details</a>
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