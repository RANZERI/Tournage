<%@page import="com.aina.spring_mvc.model.Film"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<div class="col-md-8 offset-md-2">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">  
                    <form th:action="${pageContext.request.contextPath}/Film/inserer" th:object="${film}"  method="POST" enctype="multipart/form-data">
                        Nom: <input type="text" class="form-control" name="nom" th:field="*{nom}"><br>
                        <button class="btn btn-primary">Inserer</button>
                    </form>
                </div><!-- comment -->
                <div class="col-sm-6">
                    <% 
                        List<Film> plateau=(List<Film>) request.getAttribute("liste");
                    %>
                    <small class="text-light fw-semibold">Liste Film</small>
                    <div class="demo-inline-spacing mt-3">
                        <ul class="list-group">
                            <% for(int i=0;i<plateau.size();i++){ %> 
                                <li class="list-group-item  align-items-center">
                                    <div class="row">
                                        <div class="col-sm-8">                                                        
                                            <% out.println(plateau.get(i).getNom()); %>
                                        </div>
                                        <div class="col-sm-4">
                                            <a href="${pageContext.request.contextPath}/Planning/planifier?id=<%=plateau.get(i).getId() %>">Plannifier</a>
                                        </div>
                                    </div>
                                </li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div><!-- comment -->
        </div>
    </div>        
</div>
<%@ include file="../../footer.jsp" %>