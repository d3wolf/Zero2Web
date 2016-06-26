<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><html>
<head>
    <title></title>
    <%@include file="/jsp/common.jsp" %> 
    <script type="text/javascript" language="javascript">
        $(function(){

            var myForm={};
            (function(myForm){

                myForm.initData={
                    multiChoose:[],
                    prevIndex:-1,
                    currentIndex:0,
                    nextIndex:1,
                    indexer:0,
                    blocks:'#myForm>div',
                    sep:4// sep number static
                };

                myForm.build=function(){
                    myForm.methods.hideBlock(myForm.initData.blocks);
                    $('#myForm>div>input[type=button][value=Prev]').on('click',function(){
                        myForm.methods.showPrev();
                    });
                    $('#myForm>div>input[type=button][value=Next]').on('click',function(){
                        var me=$(this);
                        myForm.methods.showNext(me);
                    });
                    $('h1').on('click',function(){
                        //初始化数据
                        myForm.initData.multiChoose.length=0;
                        myForm.initData.prevIndex=-1;
                        myForm.initData.currentIndex=0;
                        myForm.initData.nextIndex=1;
                        myForm.initData.indexer=0;
                        myForm.methods.hideBlock(myForm.initData.blocks);
                        myForm.methods.pushBlock(myForm.initData.blocks);
                        $('#myForm>.'+myForm.initData.multiChoose[0]).css('display','block');
                    });
                };

                myForm.methods=(function(){
                    return {
                        pushBlock:function(el){
                            var chooses=$(el);
                            for(var i=0;i<chooses.length;i++){
                                myForm.initData.multiChoose.push($(chooses[i]).attr('class'));
                            }
                            console.log(myForm.initData.multiChoose);
                        },
                        hideBlock:function(el){
                            $(el).each(function(){
                                $(this).css('display','none');
                            })
                        },
                        showNext:function(me){
                            if( me.attr('name')=='chooseToShowBtn' && me.attr('value')=='Next' ){
                                // chooses
                                myForm.methods.initChoose();
                            }
                            if(!myForm.initData.multiChoose[myForm.initData.nextIndex]){
                                console.log('no next');
                                return;
                            }
                            $('#myForm>.'+myForm.initData.multiChoose[myForm.initData.currentIndex]).css('display','none');
                            $('#myForm>.'+myForm.initData.multiChoose[myForm.initData.nextIndex]).css('display','block');
                            myForm.initData.prevIndex=myForm.initData.currentIndex;
                            myForm.initData.currentIndex=myForm.initData.nextIndex;
                            myForm.initData.nextIndex=myForm.initData.nextIndex+1;
                        },
                        showPrev:function(){
                            if(!myForm.initData.multiChoose[myForm.initData.prevIndex]){
                                console.log('no prev');
                                return;
                            }
                            $('#myForm>.'+myForm.initData.multiChoose[myForm.initData.currentIndex]).css('display','none');
                            $('#myForm>.'+myForm.initData.multiChoose[myForm.initData.prevIndex]).css('display','block');
                            myForm.initData.nextIndex=myForm.initData.currentIndex;
                            myForm.initData.currentIndex=myForm.initData.prevIndex;
                            myForm.initData.prevIndex=myForm.initData.prevIndex-1;
                        },
                        initChoose:function(){
                            var chs=$('#myForm>div>input[type=checkbox][name=chooseToShow]');
                            var chooseArr=[];
                            for(var i=0;i<chs.length;i++){
                                console.log(i);
                                if(chs[i].checked){
                                    chooseArr.push(chs[i].value);
                                }
                            }
                            myForm.methods.mergeArray(chooseArr);
                        },
                        mergeArray:function(chooseArr){
                            var tempArr=[];
                            for(var i=0;i<myForm.initData.sep;i++){
                                tempArr.push(myForm.initData.multiChoose[i]);
                            }
                            for(var i=0;i<chooseArr.length;i++){
                                tempArr.push(chooseArr[i]);
                            }
                            myForm.initData.multiChoose.length=0;
                            myForm.initData.multiChoose=tempArr;
                        }
                    }
                })();

            })(myForm);

            myForm.build();

        });
    </script>
    <style type="text/css">
        h1{
            cursor: pointer;
        }
    </style>

</head>
<body>

<h1>FormFlowOneArray Click Here!</h1>


<form id="myForm">
    <div class="step1">
        step1
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step2">
        step2
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step3">
        step3
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step4">
        step4

        <input type="checkbox" name="chooseToShow" value="step5"><br />
        <input type="checkbox" name="chooseToShow" value="step6"><br />
        <input type="checkbox" name="chooseToShow" value="step7"><br />
        <input type="checkbox" name="chooseToShow" value="step8"><br />
        <input type="checkbox" name="chooseToShow" value="step9"><br />

        <input type="button" name="chooseToShowBtn" value="Prev">
        <input type="button" name="chooseToShowBtn" value="Next">
    </div>

    <!-- below are chooseElements -->
    <div class="step5">
        step5
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step6">
        step6
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step7">
        step7
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step8">
        step8
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
    <div class="step9">
        step9
        <input type="button" value="Prev">
        <input type="button" value="Next">
    </div>
</form>

</body>
</html>