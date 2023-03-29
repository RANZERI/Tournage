<%-- 
    Document   : updateArticle
    Created on : 6 févr. 2023, 14:51:38
    Author     : Mendrika
--%>
<%@page import="com.aina.spring_mvc.model.Article"%>
<%@ include file="../../header.jsp" %>
<% Article art=(Article) request.getAttribute("article"); %>
<form action="" method="post"> 
    <input type="hidden" name="id" value=<% out.println(art.getId()); %> />
    <div class="row">
        <div class="col-md-4 offset-4">                                    
            <h4>Titre Article: <% out.println(art.getTitre()); %></h4>
            <h6>Date de publication</h6>
            <input type="datetime-local" value=<% out.println(art.getDate_de_publication()); %> class="form-control" name="publication" /> <br>                                   
            <div class="row">
                <div class="col-md-3">                    
                    <button class="btn btn-outline-primary">valider</button>
                </div>
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/searchArticle" class="btn btn-outline-info">Retour</a><br>
                </div>
                <b>${message}</b><br>
            </div>
        </div>
    </div>
</form>

<%@ include file="../../footer.jsp" %>
