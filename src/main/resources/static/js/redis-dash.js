function addInstance() {
    var name = $("#name").val();
    var hostname = $("#hostname").val();
    var port = $("#port").val();

    var data = {}
    data['name']= name;
    data['hostname']=hostname;
    data['port']=port;

    // TODO: Add validation on fields

    $.ajax({
        type:"POST",
        url:"/instances",
        contentType:"application/json",
        data: JSON.stringify(data),
        dataType:"json",
        success: function (data) {
            $("#addInstanceForm").trigger('reset');
            $("#addInstanceModal").modal('hide');
            var newInstanceHtml = "<div class=\"col-lg-4\">\n" +
                                        "<a href=\"instances/" + data.uuid + "\" class=\"card btn-primary text-white bg-info mb-3\"  role=\"button\">" +
                                        "<div class=\"card-body\">" +
                                            "<h5 class=\"card-title\">" + data.name + "</h5>" +
                                            "<p class=\"card-text\">" + data.hostname + ":" + data.port + "</p>" +
                                            "</div>" +
                                        "</a>" +
                                    "</div>";

            $("#instances").append(newInstanceHtml);
        },
        error:function (error) {
            console.log(error);
            alert("Error Occurred. Cannot add instance. Please try again.");
        }
    })
}

function refreshInstanceDetails() {
    var uuid = $("#uuid").val();
    $.ajax({
        type:"GET",
        url:"/instances/" + uuid + "/refresh",
        dataType:"json",
        success: function (data) {
            console.log(data)
        },
        error:function (error) {
            console.log("can not refresh details : " + error);
        }
    })
}

function keySearch() {
    var key = $("#keySearchBox").val();
    if(key.length >= 3) {
        console.log(key);
        $.ajax({
            type:"GET",
            url:"search?key="+key,
            dataType:"json",
            success: function (data) {
                console.log(data)
            },
            error:function (error) {
                console.log("can not search : " + error);
            }
        })
    }

}