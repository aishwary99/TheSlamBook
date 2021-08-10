var baseURL="/slamBook/";
    function Member()
    {
        this.name="";
        this.email="";
        this.gender="";
        this.password="";   
    }
    function SlamBookService()
    {
        this.authenticate=function(member,onSuccess,onFailure,onError){
            $.ajax({
                "url":baseURL+"authenticate",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(member),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess(responseJson.imageStatus);
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
            });
        };
        this.register=function(member,onSuccess,onFailure,onError){
            $.ajax({
                "url":baseURL+"register",
                "type":"POST",
                "dataType":"json",
                "data":JSON.stringify(member),
                "contentType":"application/json",
                "success":function(responseJson)
                {
                    if(responseJson.success=="true") onSuccess();
                    else onFailure(responseJson.exception);
                },
                "error":function()
                {
                    onError();
                }
            });
        };
    }