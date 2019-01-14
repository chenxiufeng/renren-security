var vm = new Vue({
	el:'#rrapp',
	data:{
        fileList:[],
	},
    methods: {
        submitUpload() {
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        download:function () {

            window.location =baseURL+"/upload/download";
        }
    }
});