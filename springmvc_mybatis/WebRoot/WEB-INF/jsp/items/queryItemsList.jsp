<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function editItemsList() {
		//提交form
		document.itemsForm.action = "${pageContext.request.contextPath }/items/editItemsList.action";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
	<form name="itemsForm"
		action="${pageContext.request.contextPath }/items/queryItems.action"
		method="post">
		<table width="100%" border=1>
			<tr>
				<td>操作:</td>
				<td><input type="button" value="批量修改" onclick="editItemsList()" /></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>商品描述</td>
				<td>商品生产日期</td>
			</tr>
			<c:forEach items="${itemsList }" var="item" varStatus="status">
				<tr>
					<td><input name="itemsList[${status.index }].name" value="${item.name }" /></td>
					<td><input name="itemsList[${status.index }].price" value="${item.price }" /></td>
					<td><input name="itemsList[${status.index }].detail" value="${item.detail }" /></td>
					<td><input name="itemsList[${status.index }].createtime" value="<fmt:formatDate value='${item.createtime }' pattern='yyyy-MM-dd HH:mm:ss'/>" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
