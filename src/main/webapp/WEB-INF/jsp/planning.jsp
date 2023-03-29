<%@page import="java.util.ArrayList"%>
<%@page import="com.aina.spring_mvc.model.Action"%>
<%@page import="com.aina.spring_mvc.model.Scene"%>
<%@page import="com.aina.spring_mvc.model.Planning"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<%
    List<Scene> lista=(List<Scene>) request.getAttribute("scene");
%>
<div class="col-md-8 offset-md-2">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Planning/planifier" method="POST">
                <div class="row">                    
                    <div class="col-sm-4">
                        <input type="hidden" class="form-control" name="id" value=<%=request.getAttribute("id") %>>
                        Date debut: <input type="date" class="form-control" name="dates1">
                        <br>
                        <% for(int i=0;i<lista.size();i++){%>
                        <b><% out.println(lista.get(i).getDescriptions()); %><input type="checkbox" name="scene" value=<%=lista.get(i).getId() %>></b><br>
                        <% } %>
                    </div>
                    <div class="col-sm-4">
                        Date fin: <input type="date" class="form-control" name="dates2">
                    </div>
                    <div class="col-sm-4">                
                        <button class="btn btn-primary">Plannifier</button>
                    </div>
                </div>
            </form> 
            <br>
            <form action="${pageContext.request.contextPath}/Planning/valider" method="POST">
                <input type="hidden" class="form-control" name="id" value=<%=request.getAttribute("id") %>>
                <table class="table">
                    <tr>                    
                        <th>Jour</th>
                        <th>Scene</th>
                        <th>Plateau</th>
                        <th>Acteur</th>
                        <th>Durrer</th>
                        <th>Valider</th>
                    </tr>
                    <% 
                        List<Planning> list=(List<Planning>) request.getAttribute("planning"); 
                        if(list!=null){
                    %>
                    <% for(int i=0;i<list.size();i++){ %>
                    <tr>
                        <td>
                            <input type="date" class="form-control" name="dates" value=<% out.println(list.get(i).getJour()); %> />
                            <input type="hidden" name="liste" value=<% out.println(list.get(i).getIdScene().getId()); %> >
                        </td>
                        <td><% out.println(list.get(i).getIdScene().getDescriptions()); %></td>
                        <td><% out.println(list.get(i).getIdScene().getPlateau().getNom()); %></td>
                        <td>
                            <ul>                                
                        <% 
                            List<Action> act=new ArrayList(list.get(i).getIdScene().getActions()); 
                            for(int j=0;j<act.size();j++){
                        %>
                        <li><% out.println(act.get(j).getIdActeur().getNom()); %></li>
                        <% } %>
                            </ul>
                        </td>
                        <td>
                            <%=list.get(i).getIdScene().getDurre() %>
                        </td>
                        <td>
                            <% if(list.get(i).getJour()!=null){ %>
                            <input type="checkbox" class="form-check" name="valider" value=<%=i %>>                        
                            <% }else{ %>
                            <b>Non prise en compte</b>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </table>
                <% if(list.size()!=0){ %>
                <button class="btn btn-primary">Valider</button>
                <% } %>
                <% } %>
            </form>
        </div>
    </div>        
</div>
<%@ include file="../../footer.jsp" %>