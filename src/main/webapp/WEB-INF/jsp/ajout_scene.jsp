<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.aina.spring_mvc.model.Auteur"%>
<%@page import="com.aina.spring_mvc.model.Film"%>
<%@page import="com.aina.spring_mvc.model.Plateau"%>
<%@page import="com.aina.spring_mvc.model.Scene"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<%
    List<Scene> scene=( List<Scene>)request.getAttribute("scene");
    List<Film> film=( List<Film>)request.getAttribute("film");
    List<Plateau> plateau=( List<Plateau>)request.getAttribute("plateau");
    List<Auteur> auteur=( List<Auteur>)request.getAttribute("auteur");
%>
<div class="col-md-8 offset-md-2">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">                    
                    
                    <form:form action="${pageContext.request.contextPath}/Scene/inserer" method="POST" modelAttribute="objet">
                        Description :<br>
                        <form:textarea path="descriptions" name="descriptions" cols="25" class="form-control" rows="10"></form:textarea><br>
                        Plateau: 
                        <form:select path="idPlateau.id" class="form-select" >
                            <% for(int i=0;i<plateau.size();i++){ %>
                            <form:option value="<%= plateau.get(i).getId() %>" label="<%= plateau.get(i).getNom() %>"/>
                            <% } %>                       
                        </form:select><br>
                        Film: 
                        <form:select path="idFilm.id" class="form-select">
                            <% for(int i=0;i<film.size();i++){ %>
                            <form:option value="<%= film.get(i).getId() %>" label="<%= film.get(i).getNom() %>"/>
                            <% } %>
                        </form:select><br>
                        Auteur: 
                        <form:select path="idAuteur.id" class="form-select" >
                            <% for(int i=0;i<auteur.size();i++){ %>
                            <form:option value="<%= auteur.get(i).getId() %>" label="<%= auteur.get(i).getNom() %>"/>
                            <% } %>
                        </form:select><br>
                        <input type="submit" class="btn btn-primary" value="Inserer"/>&nbsp&nbsp&nbsp&nbsp<a class="btn btn-success" href="${pageContext.request.contextPath}/Generer">Export PDF</a>
                    </form:form>
                </div>
                <div class="col-sm-6">
                    <form:form action="${pageContext.request.contextPath}/Scene/filtrer" method="POST" modelAttribute="filtrer">
                        Statut: 
                        <div class="row">
                            <div class="col-sm-6">                                
                                <form:select path="statut" class="form-select" >
                                    <form:option value="creer" label="creer"/>
                                    <form:option value="planifier" label="planifier"/>
                                </form:select><br>
                            </div>
                            <div class="col-sm-2">
                            <input type="submit" class="btn btn-primary" value="filtrer"/>
                            </div>
                        </div>
                    </form:form>
                    <table class="table">
                        <tr>
                            <th>Description</th>
                            <th>Plateau</th>
                            <th>Statut</th>
                        </tr>
                        <% for(int i=0;i<scene.size();i++){ %>
                        <tr>
                            <td><% out.println(scene.get(i).getDescriptions()); %></td>
                            <td>
                                <% 
                                    if(scene.get(i).getPlateau()!=null){
                                        out.println(scene.get(i).getPlateau().getNom()); 
                                    }
                                %>
                            </td>
                            <td><% out.println(scene.get(i).getStatut()); %></td>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
    </div>        
</div>
<%@ include file="../../footer.jsp" %>