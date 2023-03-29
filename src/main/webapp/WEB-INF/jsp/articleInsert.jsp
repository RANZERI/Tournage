<%-- 
    Document   : article
    Created on : 27 janv. 2023, 15:08:58
    Author     : Mendrika
--%>

<%@ include file="../../header.jsp" %>
<div class="col-md-4 offset-md-4">
    <div class="card mb-4">
        <h5 class="card-header">Form Controls</h5>
        <div class="card-body">
        <form th:action="@{/insererarticle}" th:object="${article}"  method="POST" enctype="multipart/form-data">
            Titre: <input type="text" class="form-control" name="titre" th:field="*{titre}"><br>
            Type: 
            <select name="type" class="form-select" id="ice" th:field="*{type}">
                <option value="">Veuillez choisir</option>
                <option value="article">Article</option>
                <option value="evenement">Evenement</option>
            </select><br>
            Description :<br>
            <textarea name="body" cols="25" class="form-control" rows="10" th:field="*{body}"></textarea><br>          
            <input type="file" class="form-control" placeholder="upload" name="file" th:field="*{file}"><br>
            <b id="xxx"></b>
            <input type="submit" value="submit" class="btn btn-primary" name="submit" >
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/searchArticle">Retour</a>
            <b>${message}</b><br>
        </form>
        </div>
    </div>
        
</div>
<%@ include file="../../footer.jsp" %>
<script type="text/javascript">
    var sel=document.getElementById("ice");
    sel.addEventListener('change',function(){
//        alert(sel.value );
        var xx=document.getElementById("xxx");
        if(sel.value=="evenement"){
            xx.innerHTML="Date debut evenement: <input type=date th:field=\"*{date1}\" class=\"form-control\" name=date1><br>Date fin evenement: <input type=date class=\"form-control\" name=date2 th:field=\"*{date2}\"><br>";
        }else{
            xx.innerHTML="Date  <input type=date name=date1 class=\"form-control\" th:field=\"*{date1}\"><br><input type=hidden class=\"form-control\" name=date2 th:field=\"*{date2}\">";              
        }     
    });
    
</script>
