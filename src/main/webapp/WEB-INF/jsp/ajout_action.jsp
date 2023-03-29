<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page import="com.aina.spring_mvc.model.Scene"%>
<%@page import="com.aina.spring_mvc.model.Action"%>
<%@page import="com.aina.spring_mvc.model.Acteur"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<% 
    List<Acteur> acteur=(List<Acteur>)request.getAttribute("acteur");
    List<Action> action=(List<Action>)request.getAttribute("action");
    List<Scene> scene=( List<Scene>)request.getAttribute("scene");
%>
<div class="col-md-8 offset-md-2">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">                    
                    <form:form action="${pageContext.request.contextPath}/Action/inserer" method="POST" modelAttribute="objet">
                        Durre: <form:input type="time" class="form-control" name="durre" path="durre"/><br>
                        Acteur: 
                        <form:select path="idActeur.id" class="form-select">
                            <% for(int i=0;i<acteur.size();i++){ %>
                            <form:option value="<%= acteur.get(i).getId() %>" label="<%= acteur.get(i).getNom() %>"/>
                            <% } %>
                        </form:select><br>
                        Scene: 
                        <form:select path="idscene.id" class="form-select" >
                            <% for(int i=0;i<scene.size();i++){ %>
                            <form:option value="<%=scene.get(i).getId() %>" label="<%=scene.get(i).getDescriptions() %>" />
                            <% } %>
                        </form:select><br>
                        Description :<br>
                        <form:textarea path="description" name="description" cols="25" class="form-control" rows="10"></form:textarea><br>
                        Dialogue :<br>
                        <form:textarea path="dialogue" name="dialogue" cols="25" class="form-control" rows="10"></form:textarea><br>
                        <input type="submit" class="btn btn-primary" value="Inserer"/>
                    </form:form>
                </div>
                
                <div class="col-sm-6">
                    <table class="table">
                        <tr>                            
                            <th>Scene</th>
                            <th>Acteur</th>
                            <th>Durre</th>
                        </tr>
                        <% for(int i=0;i<action.size();i++){ %>
                        <tr>                            
                            <td><% out.println(action.get(i).getIdscene().getDescriptions()); %></td>
                            <td><% out.println(action.get(i).getIdActeur().getNom()); %></td>
                            <td><% out.println(action.get(i).getDurre()); %></td>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
        
    </div>        
</div>
<%@ include file="../../footer.jsp" %>