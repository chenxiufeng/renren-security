jQuery.extend({
  tab_list:function(file_list){
    for(file_ in file_list)
    {
        var  tbody='<tbody class="filelist"><tr>';
        tbody+='<td><span><i></i>';
        tbody+=file_.oldFilename;
        tbody+='</span></td><td>';
        tbody+=(file_.size/1024).toFixed(2)+'KB';
        tbody+='</td><td>';
        tbody+=file_.date;
        tbody+='</td><td>';
        tbody+='<a viewUrl="'+file_.viewUrl+'" href="javascript:" class="file_download">预览</a>';
        tbody+='<a href="javascript:" class="file_dale_only" file_id="'+file_.id+'">删除</a>';
        tbody+=' </td></tr></tbody>';
        $(".member_file_list").append(tbody);
    }

  },
  files_up:function(json,type,errorFn)
  {
			var int_=-1;
			var index_number=0;
			// 实例化
			var uploader = WebUploader.create(json);
			
			$(document).on("click",".file-operate",function(){
	
				var file_id=$(this).attr("file_id");
				uploader.removeFile(uploader.getFile(file_id));//删除文件
				int_-=1;
				$(this).parents('li').remove();
				
			})
			$(json.pick.id).each(function(i, o) {
                $(o).click(function(){
					index_number=i;
				})
            });
			uploader.on('beforeFileQueued',function(file){
				if($('.files_upload').length<1)//避免重复添加
				{
					uploader_list()
				}
				file.on('statuschange', function(cur, prev) {
					//console.log(cur)
					//console.log(prev)
				})
			})
			
			// 当有文件被添加进队列的时候
			uploader.on( 'filesQueued', function( file ) {
				int_+=1;
				uploader.options.formData.indexNum = index_number;
				add_list_box(file[0].name,(file[0].size/1024/1024).toFixed(2),file[0].id,'');
				uploader.upload();//开始上传
				$(json.pick.id).eq(index_number).attr("id",file[0].id)
	
			});
			// 文件上传过程中创建进度条实时显示。
			uploader.on( 'uploadProgress', function( file, percentage ) {
					$(".container_uploaderList .uploading").eq(int_).text(+ parseInt(percentage * 100) + '%');
					if(typeof fileUpProgress === "function"){
                        fileUpProgress(file,percentage,file.id);
					}
			});
			  uploader.on( 'all', function( type ) { 
			  });
			  
			//上传成功时候
			uploader.on( 'uploadSuccess', function( file, response) {
			   /* var filePath_new=eval('(' + response._raw + ')').filePath;
				var $li = $('#'+file.id);
				var fileEvent = {
					queueId: file.id,
					name: file.name,
					size: file.size,
					type: file.type,
					filePath: filePath_new
				};
				$inputf = $('<input type="hidden" name="filesInputName['+int_+']" value="' + filePath_new+ '"/>').appendTo($li);
				$('<input type="hidden" name="file_name['+int_+']" value="' + file.name + '"/>').appendTo($li);
				$('<input type="hidden" name="file_size['+int_+']" value="' + file.size + '"/>').appendTo($li);
				$('<input type="hidden" name="file_type['+int_+']" value="' + file.type + '"/>').appendTo($li);
				$li.attr('data-file_url',fileEvent.filePath);*/
				
				
				var file_all=eval('(' + response._raw + ')');
				if(file_all.error)
				{
					$(".container_uploaderList .uploading").eq(int_).text('上传失败');
					if(errorFn)
					 {
						errorFn(file);
					}
					return false	
				}
				$(".container_uploaderList .uploading").eq(int_).text('上传成功');
				if(type)
				{
					type(response.indexNum,response,file);
					return false;
				}
				if($(".member_file_list").length>=1)
				{
					var  tbody='<tbody class="filelist"><tr>';
						 tbody+='<td><input type="checkbox" value="'+file_all.id+'"></td>';
						 tbody+='<td><span><i></i>';
						 tbody+=file_all.name;	
						 tbody+='</span></td><td>';
						 tbody+=(file_all.size/1024/1024).toFixed(2)+'M';
						 tbody+='</td><td>';
						 tbody+=file_all.createTime;
						 tbody+='</td><td>';
						 tbody+='<a href="/member/file-download/' + file_all.id +'" class="file_download"><i></i>下载</a>';
						 tbody+='<a href="javascript:" class="file_dale_only" file_id="'+file_all.id+'"><i></i>删除</a>';
						 tbody+=' </td></tr></tbody>';
						 
					$(".member_file_list").append(tbody);
					
				}
			});
	
			//上传出错时候
			uploader.on( 'uploadError', function( file ) {
				 alert('上传出错');
				 if(errorFn)
				 {
					errorFn(file);
				}
			});
			//不管成功或者失败，文件上传完成时触发。
			uploader.on( 'uploadComplete', function( file ) {
			});
			uploader.onError = function( code) {//goayangdi
								
				var msg = '';
				switch( code ) {
					case 'Q_TYPE_DENIED':
						json.custom_type ? msg ="您选择的文件类型不匹配，支持文件："+json.custom_type.type : msg = '您选择的文件类型不匹配，支持文件：jpg,png,gif,psd,ai,cdr,eps,ppt,word,excel,pdf,tiff,rar,zip,7z';
						break;
					case 'F_DUPLICATE':
						msg = '您选择文件已经在上传列表中了！';
						break;
					case 'Q_EXCEED_NUM_LIMIT':
						msg = '已超过最大上传文件数';
						break;
					case 'Q_EXCEED_SIZE_LIMIT':
						json.custom_type ? msg ="最大支持"+json.custom_type.size+"文件上传" : msg = '最大支持20M文件上传';
						break;
					default :
						msg = code;
						break;
	
				}
			   alert(msg);
			};
	
		//上传列表
		
		function uploader_list()
		{
			var list_boc='<div class="files_upload">'
				list_boc+='<div class="dialog-header">'
				list_boc+='<div class="dialog-control">'
				list_boc+='<span class="icon icon-minimize" style="font-size:35px; vertical-align: top;">f</span>'
				list_boc+='<span class="icon icon-close">×</span>'
				list_boc+='</div>'
				list_boc+='</div>'
				//list_boc+='<div class="tips has-error">'
				//list_boc+='<div class="text">有1个文件上传成功</div>'
				//list_boc+='<em class="close">×</em>'
				//list_boc+='</div>'
				list_boc+='<div class="uploader-list-header">'
				list_boc+='<span class="file-name">文件(夹)名</span>'
				list_boc+='<span class="file-size">大小</span>'
				list_boc+='<span class="file-status">状态</span>'
				list_boc+='<span>操作</span>'
				list_boc+='</div>'
				list_boc+='<div class="uploader_list_all">'
				list_boc+='<ul class="container_uploaderList">'
				list_boc+='</ul></div></div>';
			$(document.body).append(list_boc)	
		}
		//
		function  add_list_box(file_name,file_size,file_id,state)
		{
			var add_list='<li>'
				add_list+='<div class="file-name" title="'+file_name+'">'+file_name+'</div>'
				add_list+='<div class="file-size">'+file_size+'M</div>'
				add_list+=' <div class="file-status">'
			   // add_list+='<span class="'+state+'">排队中…</span>'
			   // add_list+='<span class="'+state+'">准备上传…</span>'
				add_list+='<span class=uploading>0.00%</span>'
				//add_list+=' <span class="'+state+'"><em></em>服务器错误</span>'
			   // add_list+='<span class="'+state+'"><em></em><i>已暂停</i></span>'
			   // add_list+='<span class="'+state+'"><em></em><i>已取消</i></span>'
			   // add_list+='<span class="'+state+'"><em></em><i>秒传</i></span>'
				add_list+='</div>'
				add_list+='<div class="file-operate" file_id='+file_id+'>删除</div></li>'
			$('.container_uploaderList').append(add_list);	
			
		}
		
		
		//关闭小窗口
		$(document).on('click','.icon-close',function(){
			int_=-1;
			for(var i=0; i<$('.file-operate').length;i++)
			{
				uploader.removeFile(uploader.getFile($('.file-operate').eq(i).attr('file_id')));//删除文件	
			}
			
	
			$('.files_upload').remove();	
		})
		
		$(document).on('click','.icon-minimize',function(){
			if($(this).text()!='e')
			{
				$(this).text('e')
				$('.files_upload').height('46');
			}
			else{
				$(this).text('f')
				$('.files_upload').css("height","auto");
			}
				
		})  
  },
    files_up_oss:function(json,type,isShowList)
    {

		var index_number=0;
	 /* $(json.id).each(function(i, o) {
			  $(o).click(function(){
				  index_number=i;
			  })
		  });*/

		/*----------------------------------------------------------------*/
        var index_number=0;
        var accessid = ''
        var accesskey = ''
        var host = ''
        var policyBase64 = ''
        var signature = ''
        var callbackbody = ''
        var callbackbodyvar = ''
        var filename = ''
        var key = ''
        var expire = 0
        var g_object_name = ''
        var g_object_name_type = ''
        var realFileName = ''
        var newFileName	=''
        var now = timestamp = Date.parse(new Date()) / 1000;
        for(var i=0;i<json.id.length;i++)
        {
            $("#"+json.id[i]).click(function(){
                index_number=json.id.indexOf($(this).attr("id"));
            })
        }
        function send_request(ori_filename)
        {
            var xmlhttp = null;
            if (window.XMLHttpRequest)
            {
                xmlhttp=new XMLHttpRequest();
            }
            else if (window.ActiveXObject)
            {
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }

            if (xmlhttp!=null)
            {
                serverUrl = json.url;
                serverUrl += "?ori_filename="+ori_filename;
                if(json.dir){
                    serverUrl +="&dir="+json.dir;
                }
                xmlhttp.open( "GET", serverUrl, false );
                xmlhttp.send(null );
                return xmlhttp.responseText
            }
            else
            {
                alert("Your browser does not support XMLHTTP.");
            }
        };

        function check_object_radio() {
			/*	var tt = document.getElementsByName('myradio');
			 for (var i = 0; i < tt.length ; i++ )
			 {
			 if(tt[i].checked)
			 {
			 g_object_name_type = tt[i].value;
			 break;
			 }
			 }*/
        }

        function get_signature(ori_filename)
        {
            //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲

            body = send_request(ori_filename)
            var obj = eval ("(" + body + ")");
            host = obj['host']
            policyBase64 = obj['policy']
            accessid = obj['accessid']
            signature = obj['signature']
            expire = parseInt(obj['expire'])
            callbackbody = obj['callback']
            callbackbodyvar = JSON.parse(obj['callback_var'])
            key = obj['dir']
            newFileName = obj['newFileName']
            realFileName = obj['ori_filename']
        };

        function random_string(len) {
            len = len || 32;
            var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
            var maxPos = chars.length;
            var pwd = '';
            for (i = 0; i < len; i++) {
                pwd += chars.charAt(Math.floor(Math.random() * maxPos));
            }
            return pwd;
        }

        function get_suffix(filename) {
            pos = filename.lastIndexOf('.')
            suffix = ''
            if (pos != -1) {
                suffix = filename.substring(pos)
            }
            return suffix;
        }

        function calculate_object_name(filename)
        {
            if (g_object_name_type == 'local_name')
            {
                g_object_name += "${filename}"
            }
            else if (g_object_name_type == 'random_name')
            {
                suffix = get_suffix(filename)
                g_object_name = key + random_string(10) + suffix
            }
            return ''
        }

        function set_upload_param(up, filename)
        {

            g_object_name = key+"${filename}";
            if (filename != '') {
                suffix = get_suffix(filename)
                calculate_object_name(filename)
            }
            get_signature(filename)
            new_multipart_params = {
                'key' : key+newFileName,
                'policy': policyBase64,
                'OSSAccessKeyId': accessid,
                'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
                'callback' : callbackbody,
                'signature': signature,
                'Content-Disposition':'filename='+realFileName,  //让oss保存真实文件名
                //'callback_var' : callbackbodyvar,
            };
			
            for(var p in callbackbodyvar){  //附带自定义回调参数
                new_multipart_params[p]=callbackbodyvar[p]
            }
            up.setOption({
                'url': host,
                'multipart_params': new_multipart_params
            });
            up.start();
        }

        //上传列表
        function uploader_list()
        {
            var list_boc='<div class="files_upload">'
            list_boc+='<div class="dialog-header">'
            list_boc+='<div class="dialog-control">'
            list_boc+='<span class="icon icon-minimize" style="font-size:35px; vertical-align: top;">f</span>'
            list_boc+='<span class="icon icon-close">×</span>'
            list_boc+='</div>'
            list_boc+='</div>'
            //list_boc+='<div class="tips has-error">'
            //list_boc+='<div class="text">有1个文件上传成功</div>'
            //list_boc+='<em class="close">×</em>'
            //list_boc+='</div>'
            list_boc+='<div class="uploader-list-header">'
            list_boc+='<span class="file-name">文件(夹)名</span>'
            list_boc+='<span class="file-size">大小</span>'
            list_boc+='<span class="file-status">状态</span>'
            //list_boc+='<span>操作</span>'
            list_boc+='</div>'
            list_boc+='<div class="uploader_list_all">'
            list_boc+='<ul class="container_uploaderList">'
            list_boc+='</ul>'
			list_boc+='<div style="position: absolute;bottom: 0px;left:0px; text-align: center;overflow: hidden;line-height: 30px;padding: 10px 0px;width: 100%;"><a href="javascript:" style="padding: 10px 50px; background: #337ab7;color: #ffffff;font-size: 14px; border-radius:4px;" class="start_up">开始上传</a></div></div>';
            $(document.body).append(list_boc)

        }
        //
        function  add_list_box(file_name,file_size,file_id,state)
        {
            var add_list='<li id='+file_id+'>'
            add_list+='<div class="file-name" title="'+file_name+'">'+file_name+'</div>'
            add_list+='<div class="file-size">'+file_size+'</div>'
            add_list+=' <div class="file-status">'
            // add_list+='<span class="'+state+'">排队中…</span>'
            // add_list+='<span class="'+state+'">准备上传…</span>'
            add_list+='<span class=uploading>0.00%</span>'
            //add_list+=' <span class="'+state+'"><em></em>服务器错误</span>'
            // add_list+='<span class="'+state+'"><em></em><i>已暂停</i></span>'
            // add_list+='<span class="'+state+'"><em></em><i>已取消</i></span>'
            // add_list+='<span class="'+state+'"><em></em><i>秒传</i></span>'
            add_list+='</div>'
            add_list+='<div class="file-operate" file_id='+file_id+'>删除</div></li>'
            $('.container_uploaderList').append(add_list);

        }
		/*----------------------------------------------------------------*/
        var uploader = new plupload.Uploader({
            runtimes : 'html5,flash,html4',
            browse_button :json.id,
            //multi_selection: false,
            //container: document.getElementById('area_mend_head'),
            flash_swf_url : '/js/Moxie.swf',
            silverlight_xap_url : '/js/Moxie.xap',
            //url : 'http://oss.aliyuncs.com',
            url : 'https://ziyunoss.oss-cn-hangzhou.aliyuncs.com/',

            filters: {
                mime_types : [ //只允许上传图片和zip,rar文件
                    //{ title : "Image files", extensions : "jpg,gif,png,bmp" },
                    //{ title : "Zip files", extensions : "zip,rar" }
                    { title : "*", extensions :json.file_type }
                ],
                max_file_size : json.size,//'10gb', //最大只能上传10mb的文件
                prevent_duplicates : true //不允许选取重复文件
            },

            init: {
                PostInit: function() {
                    //console.log("初始化")
					$(document).on("click",".start_up",function(){
						if (uploader.total.queued>0)
						{
							set_upload_param(uploader,'');

						}
						
					})
					
                },

                FilesAdded: function(up, files) {//console.log("文件添加")
                    plupload.each(files, function(file) {
                        if(isShowList != true){

                            if($('.files_upload').length<1)//避免重复添加
                            {
                                uploader_list()
                            }
                            add_list_box(file.name,plupload.formatSize(file.size),file.id,'')

                        }

                       
                    });

                },

                BeforeUpload: function(up, file) {//console.log("文件上传前")
					 set_upload_param(up, file.name);

                },

                UploadProgress: function(up, file) {//console.log("上传进行中");

                    $("#"+file.id).find(".uploading").text(file.percent+"%")
                },

                FileUploaded: function(up, file, info) {//console.log("上传成功")
                    if (info.status == 200)
                    {
                        $("#"+file.id).find(".uploading").text("完成");
                        var file_=JSON.parse(info.response);
                        if(type)
                        {
                            type(index_number,file_);
                            return false;
                        }
                        if($(".member_file_list").length>=1)
                        {
                            var viewUrl;
                            if(json.view_url){
                                viewUrl = json.view_url+'/'+file_.filename;
                            }
                            var  tbody='<tbody class="filelist"><tr>';
                            tbody+='<td><span><i class="'+file.id+'"></i>';
                            tbody+=file_.oldFilename;
                            tbody+='</span></td><td>';
                            tbody+=(file_.size/1024).toFixed(2)+'KB';
                            tbody+='</td><td>';
                            tbody+=file_.date;
                            tbody+='</td><td>';
                            tbody+='<a viewUrl="'+viewUrl+'" href="javascript:" class="file_download">预览</a>';
                            tbody+='<a href="javascript:" class="file_dale_only" file_id="'+file.id+'">删除</a>';
                            tbody+=' </td></tr></tbody>';

                            $(".member_file_list").append(tbody);
                            if(file_.mimeType=="image/png" || file_.mimeType=="image/jpeg"){
                                $("."+file.id).css("background","url("+ viewUrl+")")
                                $(document).on('click','.file_download',function(){
                                    layer.open({
                                        type: 1,
                                        area: ['420px', '240px'], //宽高
                                        content: '<img style="width:100%; height: 100%" src='+viewUrl+' />'
                                    });

                                })
                            }
                        }
                        // document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + get_uploaded_object_name(file.name);
                    }
                    else
                    {
                        $("#"+file.id).find(".uploading").text(info.response)
                        // document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
                    }
                },

                Error: function(up, err ) {//console.log("上传失败")
                    var msg = '';
                    switch( err.code ) {
                        case -601:
                            msg = '您选择的文件类型不匹配，支持文件：jpg,png,gif,psd,ai,cdr,eps,ppt,word,excel,pdf,tiff,rar,zip,7z';
                            break;
                        case -602:
                            msg = '您选择文件已经在上传列表中了！';
                            break;
                        case -600:
                            msg = '文件太大';
                            break;
                        default :
                            msg = "\nError xml:" + err.response;
                            break;

                    }
                    alert(msg);



                }
            }
        })
        uploader.init();
        //关闭小窗口
        $(document).on('click','.icon-close',function(){
            for(var i=0; i<$('.file-operate').length;i++)
            {
                uploader.removeFile(uploader.getFile($('.file-operate').eq(i).attr('file_id')));//删除文件
            }


            $('.files_upload').remove();
        })
		$(document).on("click",".file-operate",function(){
            var file_id=$(this).attr("file_id");
            uploader.removeFile(uploader.getFile(file_id));//删除文件
            $(this).parents('li').remove();

        })

        $(document).on("click",".file_dale_only",function(){
            $(this).parents('tbody').remove();

        })

        $(document).on('click','.icon-minimize',function(){
            if($(this).text()!='e')
            {
                $(this).text('e')
                $('.files_upload').height('46');
            }
            else{
                $(this).text('f')
                $('.files_upload').css("height","auto");
            }

        })
		/*----------------------------------------------------------------*/
    }
})


