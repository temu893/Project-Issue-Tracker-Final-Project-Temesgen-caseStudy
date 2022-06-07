const  onDeleteProectClicked = projectId =>{
$.ajax({
    url:'/api/project/'+ projectId,
    type:'DELETE',
    success:()=>{
        //response for error code 200
        location.reload();
    }
});
}
