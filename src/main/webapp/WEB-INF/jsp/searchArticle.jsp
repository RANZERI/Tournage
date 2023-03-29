<%-- 
    Document   : serachArticle
    Created on : 27 janv. 2023, 14:50:16
    Author     : Mendrika
--%>

<%@page import="com.aina.spring_mvc.gestion.Convertisseur"%>
<%@page import="java.util.List"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../navbar.jsp" %>
<script>
let items = [];
for(let cc=0;cc<document.getElementsByClassName("itemsa").length;cc++){
    items.push(cc+1);
}
let pageSize = 6;
let pages = [];

for (let i = 0; i < items.length; i += pageSize) {
  let xxx=i + pageSize;
  if(xxx>items.length){
      xxx=items.length;
  }
  pages.push(items.slice(i, xxx));
}

let currentPage = 0;

function showPage(pageNumber) {
  document.querySelectorAll(".itemsa").forEach(item => {
    item.style.display = "none";
  });
  pages[pageNumber].forEach(item => {
    document.getElementById(item).style.display = "block";
  });
}

showPage(currentPage);

document.querySelector(".prev").addEventListener("click", function() {
  if (currentPage > 0) {
    currentPage--;
    showPage(currentPage);
  }
});

document.querySelector(".next").addEventListener("click", function() {
  if (currentPage < pages.length - 1) {
    currentPage++;
    showPage(currentPage);
  }
});    
</script>    
    
<%@ include file="../../footer.jsp" %>
