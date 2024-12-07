<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JFSD Spring Demo</title>
</head>
<body>
	<h3>College Details</h3>
	<table style="width:100%">
		<tr>
			<td style="width:120px">College Name*</td>
			<td> <input type="text" id="T1" /> </td>
		</tr>
		<tr>
			<td style="width:120px">Acronym*</td>
			<td> <input type="text" id="T2" /> </td>
		</tr>
		<tr>
			<td style="width:120px"></td>
			<td>
				<button onclick="save()">Save</button> 
				<button onclick="update()">Update</button> 
				<button onclick="del()">Delete</button> 
				<button onclick="read()">Read</button> 
			</td>
		</tr>
	</table>
	
	<div style="width:100%" id="collegelist"></div>
</body>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript">
	function save(){
		var data = JSON.stringify({
			name: T1.value,
			acronym: T2.value
		});
		var url = "http://localhost:8080/10_SPRINGS17/college/save";
		callApi("POST", url, data, getResponse);
	}
	function update(){
		var data = JSON.stringify({
			name: T1.value,
			acronym: T2.value
		});
		var url = "http://localhost:8080/10_SPRINGS17/college/update";
		callApi("PUT", url, data, getResponse);
	}
	function del(){
		var data = JSON.stringify({
			name: T1.value,
			acronym: T2.value
		});
		var url = "http://localhost:8080/10_SPRINGS17/college/delete?acronym=" + T2.value;
		callApi("DELETE", url, data, getResponse);
	}
	function getResponse(response) {
		alert(response);
	}
	function read(){
		var data = "";
		var url = "http://localhost:8080/10_SPRINGS17/college/read";
		callApi("GET", url, data, generateTable)
	}
	function genrateTable(response){
		var data = JSON.parse(response);
		var tbl = `<table border="1">
						<tr>
							<th>College Name</th>
							<th>Acronym</th>
						</tr>`;
		for(var x in data){
			tbl += `<tr>
						<td>`+ data[x].name +`</td>
						<td>`+ data[x].acronym +`</td>
					</tr>`;
		}
		tbl += `</table>`;
		collegelist.innerHTML = tbl;
	}
</script>
</html>