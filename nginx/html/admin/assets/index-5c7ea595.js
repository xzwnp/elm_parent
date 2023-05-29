import{i as P,u as L,T as q}from"./index-02e4452f.js";import{a as S,u as _,g as z,d as G}from"./business-f5d858fe.js";import{d as k,L as I,k as B,_ as U,r as d,o as h,q as V,w as o,b as a,c as T,F as O,v as j,j as E,x as J,a as v,e as C,y as M,z as H,l as K}from"./index-45934075.js";const A=[{value:1,label:"美食"},{value:2,label:"小吃"},{value:3,label:"糕点"}],N=[{value:1,label:"今天"},{value:2,label:"明天"},{value:3,label:"后天"}],Q=k({components:{Layer:I,imageUploader:P},props:{layer:{type:Object,default:()=>({show:!1,title:"",showButton:!0})}},setup(e,l){const i=B(null),c=B(null);let p=B({name:"",cover:""});const g={name:[{required:!0,message:"请输入姓名",trigger:"blur"}],number:[{required:!0,message:"请输入数字",trigger:"blur"}],choose:[{required:!0,message:"请选择",trigger:"blur"}],radio:[{required:!0,message:"请选择",trigger:"blur"}]};r();function r(){e.layer.row&&(p.value=JSON.parse(JSON.stringify(e.layer.row)))}return{form:p,rules:g,layerDom:c,ruleForm:i,selectData:A,radioData:N,uploadUrl:L}},methods:{submit(){this.ruleForm&&this.ruleForm.validate(e=>{if(e){let l=this.form;this.layer.row?this.updateForm(l):this.addForm(l)}else return!1})},addForm(e){S(e).then(l=>{this.$message({type:"success",message:"新增成功"}),this.$emit("getTableData",!0),this.layerDom&&this.layerDom.close()})},updateForm(e){_(e).then(l=>{this.$message({type:"success",message:"编辑成功"}),this.$emit("getTableData",!1),this.layerDom&&this.layerDom.close()})}}});function R(e,l,i,c,p,g){const r=d("el-input"),t=d("el-form-item"),y=d("image-uploader"),n=d("el-option"),D=d("el-select"),m=d("el-form"),b=d("Layer",!0);return h(),V(b,{layer:e.layer,onConfirm:e.submit,ref:"layerDom"},{default:o(()=>[a(m,{model:e.form,rules:e.rules,ref:"ruleForm","label-width":"120px","label-position":"left",style:{"margin-left":"50px","margin-right":"30px"}},{default:o(()=>[a(t,{label:"商家名称",prop:"businessName"},{default:o(()=>[a(r,{modelValue:e.form.businessName,"onUpdate:modelValue":l[0]||(l[0]=u=>e.form.businessName=u),placeholder:"商家名称"},null,8,["modelValue"])]),_:1}),a(t,{label:"商家密码",prop:"password"},{default:o(()=>[a(r,{modelValue:e.form.password,"onUpdate:modelValue":l[1]||(l[1]=u=>e.form.password=u),placeholder:"请输入密码"},null,8,["modelValue"])]),_:1}),a(t,{label:"商家地址",prop:"businessAddress"},{default:o(()=>[a(r,{modelValue:e.form.businessAddress,"onUpdate:modelValue":l[2]||(l[2]=u=>e.form.businessAddress=u),placeholder:"商家地址"},null,8,["modelValue"])]),_:1}),a(t,{label:"商家介绍",prop:"businessExplain"},{default:o(()=>[a(r,{modelValue:e.form.businessExplain,"onUpdate:modelValue":l[3]||(l[3]=u=>e.form.businessExplain=u),placeholder:"商家介绍"},null,8,["modelValue"])]),_:1}),a(t,{label:"商家图片",prop:"cover"},{default:o(()=>[a(y,{"image-url":e.form.cover,"onUpdate:image-url":l[4]||(l[4]=u=>e.form.cover=u)},null,8,["image-url"])]),_:1}),a(t,{label:"点餐分类",prop:"type"},{default:o(()=>[a(D,{modelValue:e.form.type,"onUpdate:modelValue":l[5]||(l[5]=u=>e.form.type=u),placeholder:"请选择",clearable:""},{default:o(()=>[(h(!0),T(O,null,j(e.selectData,u=>(h(),V(n,{key:u.value,label:u.label,value:u.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),a(t,{label:"起送费",prop:"startPrice"},{default:o(()=>[a(r,{modelValue:e.form.startPrice,"onUpdate:modelValue":l[6]||(l[6]=u=>e.form.startPrice=u),placeholder:"起送费"},null,8,["modelValue"])]),_:1}),a(t,{label:"配送费",prop:"deliveryPrice"},{default:o(()=>[a(r,{modelValue:e.form.deliveryPrice,"onUpdate:modelValue":l[7]||(l[7]=u=>e.form.deliveryPrice=u),placeholder:"配送费"},null,8,["modelValue"])]),_:1}),a(t,{label:"备注",prop:"remarks"},{default:o(()=>[a(r,{modelValue:e.form.remarks,"onUpdate:modelValue":l[8]||(l[8]=u=>e.form.remarks=u),placeholder:"备注"},null,8,["modelValue"])]),_:1}),a(t,{label:"红包",prop:"redPacket"},{default:o(()=>[a(r,{modelValue:e.form.redPacket,"onUpdate:modelValue":l[9]||(l[9]=u=>e.form.redPacket=u),placeholder:"红包"},null,8,["modelValue"])]),_:1}),a(t,{label:"评分",prop:"score"},{default:o(()=>[a(r,{modelValue:e.form.score,"onUpdate:modelValue":l[10]||(l[10]=u=>e.form.score=u),placeholder:"评分"},null,8,["modelValue"])]),_:1}),a(t,{label:"折扣",prop:"discounts"},{default:o(()=>[a(r,{modelValue:e.form.discounts,"onUpdate:modelValue":l[11]||(l[11]=u=>e.form.discounts=u),placeholder:"折扣"},null,8,["modelValue"])]),_:1}),a(t,{label:"销量",prop:"sellCount"},{default:o(()=>[a(r,{modelValue:e.form.sellCount,"onUpdate:modelValue":l[12]||(l[12]=u=>e.form.sellCount=u),placeholder:"销量"},null,8,["modelValue"])]),_:1}),a(t,{label:"热门评论",prop:"hotComment"},{default:o(()=>[a(r,{modelValue:e.form.hotComment,"onUpdate:modelValue":l[13]||(l[13]=u=>e.form.hotComment=u),placeholder:"热门评论"},null,8,["modelValue"])]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["layer","onConfirm"])}const W=U(Q,[["render",R]]),X=k({name:"crudTable",components:{Table:q,Layer:W},setup(){const e=E({input:""}),l=E({show:!1,title:"新增",showButton:!0}),i=E({index:1,size:20,total:0}),c=B(!0),p=B([]),g=B([]),r=m=>{g.value=m},t=m=>{c.value=!0,m&&(i.index=1);let b={page:i.index,pageSize:i.size,...e};z(b).then(u=>{console.log(u);let s=u.data.list;Array.isArray(s)&&s.forEach(f=>{const w=A.find(F=>F.value===f.choose);w?f.chooseName=w.label:f.chooseName=f.choose;const $=N.find(F=>F.value===f.radio);$?f.radioName=$.label:f.radio}),p.value=u.data.list,i.total=Number(u.data.total)}).catch(u=>{p.value=[],i.index=1,i.total=0}).finally(()=>{c.value=!1})},y=m=>{let b=m.map(u=>u.businessId);G(b).then(u=>{K({type:"success",message:"删除成功"}),t(p.value.length===1)})},n=()=>{l.title="新增数据",l.show=!0,delete l.row},D=m=>{l.title="编辑数据",l.row=m,l.show=!0};return t(!0),{query:e,tableData:p,chooseData:g,loading:c,page:i,layer:l,handleSelectionChange:r,handleAdd:n,handleEdit:D,handleDel:y,getTableData:t}}}),Y={class:"layout-container"},Z={class:"layout-container-form flex space-between"},x={class:"layout-container-form-handle"},ee={class:"layout-container-form-search"},le={class:"layout-container-table"};function ae(e,l,i,c,p,g){const r=d("el-button"),t=d("el-popconfirm"),y=d("el-input"),n=d("el-table-column"),D=d("el-image"),m=d("Table"),b=d("Layer"),u=J("loading");return h(),T("div",Y,[v("div",Z,[v("div",x,[a(r,{type:"primary",icon:"el-icon-circle-plus-outline",onClick:e.handleAdd},{default:o(()=>[C("新增")]),_:1},8,["onClick"]),a(t,{title:"批量删除",onConfirm:l[0]||(l[0]=s=>e.handleDel(e.chooseData))},{reference:o(()=>[a(r,{type:"danger",icon:"el-icon-delete",disabled:e.chooseData.length===0},{default:o(()=>[C("批量删除")]),_:1},8,["disabled"])]),_:1})]),v("div",ee,[a(y,{modelValue:e.query.input,"onUpdate:modelValue":l[1]||(l[1]=s=>e.query.input=s),placeholder:"请输入关键词进行检索",onChange:l[2]||(l[2]=s=>e.getTableData(!0))},null,8,["modelValue"]),a(r,{type:"primary",icon:"el-icon-search",class:"search-btn",onClick:l[3]||(l[3]=s=>e.getTableData(!0))},{default:o(()=>[C("搜索")]),_:1})])]),v("div",le,[M((h(),V(m,{ref:"table",page:e.page,"onUpdate:page":l[4]||(l[4]=s=>e.page=s),showIndex:!0,showSelection:!0,data:e.tableData,onGetTableData:e.getTableData,onSelectionChange:e.handleSelectionChange},{default:o(()=>[a(n,{prop:"businessId",label:"商家编号","header-align":"center",align:"center"}),a(n,{prop:"businessName",label:"商家名称","header-align":"center",align:"center"}),a(n,{prop:"businessAddress",label:"商家地址","header-align":"center",align:"center"}),a(n,{prop:"businessExplain",label:"商家介绍","header-align":"center",align:"center"}),a(n,{prop:"cover",label:"商家图片","header-align":"center",align:"center"},{default:o(s=>[a(D,{src:s.row.cover},null,8,["src"])]),_:1}),a(n,{prop:"type",label:"点餐分类","header-align":"center",align:"center"}),a(n,{prop:"startPrice",label:"起送费","header-align":"center",align:"center"}),a(n,{prop:"deliveryPrice",label:"配送费","header-align":"center",align:"center"}),a(n,{prop:"remarks",label:"备注","header-align":"center",align:"center"}),a(n,{prop:"redPacket",label:"红包","header-align":"center",align:"center"}),a(n,{prop:"score",label:"评分","header-align":"center",align:"center"}),a(n,{prop:"discounts",label:"折扣","header-align":"center",align:"center"}),a(n,{prop:"sellCount",label:"销量","header-align":"center",align:"center"}),a(n,{prop:"hotComment",label:"热门评论","header-align":"center",align:"center"}),a(n,{label:"操作",align:"center",fixed:"right",width:"200"},{default:o(s=>[a(r,{onClick:f=>e.handleEdit(s.row)},{default:o(()=>[C("编辑")]),_:2},1032,["onClick"]),a(t,{title:"删除",onConfirm:f=>e.handleDel([s.row])},{reference:o(()=>[a(r,{type:"danger"},{default:o(()=>[C("删除")]),_:1})]),_:2},1032,["onConfirm"])]),_:1})]),_:1},8,["page","data","onGetTableData","onSelectionChange"])),[[u,e.loading]]),e.layer.show?(h(),V(b,{key:0,layer:e.layer,onGetTableData:e.getTableData},null,8,["layer","onGetTableData"])):H("",!0)])])}const te=U(X,[["render",ae]]);export{te as default};
