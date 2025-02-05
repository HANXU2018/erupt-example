<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>run</title>
</head>

<body>
    <div class="main">
        <div class="title">
            <img src="https://cdn.jellow.site/FrNoNa-sk788pA-xgH9qrpN6quWh.jpg?imageMogr2/auto-orient/heic-exif/1/format/jpeg/thumbnail/300x300%3E"
                alt="" />
            <div> 码不停Ti 排行榜</div>
        </div>
        <div class="list-box">
            <#list us as key>
                <div class="list">
                    <div class="number"><b>${key.number}</b></div>
                    <div class="user">
                        <img src=${key.img} class="head-url" alt="" />
                        <#if (key.number>0)&& (key.number<=3) >
                            <img src= "/img/${key.number}.png"  class="level" alt="" />
                        </#if>

                        <div>
                            <p class="good">${key.good}</p>
                            <p class="jk-num">
                                <#if (key.title)??>
                                    <span>${key.title}</span>
                                </#if>
                                ${key.jknum}
                            </p>
                        </div>
                    </div>
                    <div class="km">${key.km}KM</div>
                </div>


            </#list>

        </div>


        <div class="list-box">
            <div class="list">
                <div class="number">1</div>
                <div class="user">
                    <img src="https://cdn.jellow.site/FrNoNa-sk788pA-xgH9qrpN6quWh.jpg?imageMogr2/auto-orient/heic-exif/1/format/jpeg/thumbnail/300x300%3E"
                        class="head-url" alt="" />
                    <img src="/img/1.png" class="level" />
                    <div>
                        <p class="good">迷思特玛</p>
                        <p class="jk-num">
                            <span>状元</span>JK0001
                        </p>
                    </div>
                </div>
                <div class="km">227KM</div>
            </div>
            <div class="list">
                <div class="number">2</div>
                <div class="user">
                    <img src="https://cdn.jellow.site/FtL6tp7h5AMYRfXOBNKAP5y1aEKy.jpg?imageMogr2/auto-orient/heic-exif/1/format/jpeg/thumbnail/300x300%3E"
                        class="head-url" alt="" />
                    <img src="/img/2.png" class="level" />
                    <div>
                        <p class="good">爱老公比永远多一天</p>
                        <p class="jk-num">
                            <span>榜眼</span>JK0002
                        </p>
                    </div>
                </div>
                <div class="km">174KM</div>
            </div>
            <div class="list">
                <div class="number">3</div>
                <div class="user">
                    <img src="https://cdn.jellow.site/FpRJq5d3pAXGT4S79TrsN8pMlSD4.png?imageMogr2/auto-orient/heic-exif/1/format/jpeg/thumbnail/300x300%3E"
                        class="head-url" alt="" />
                    <img src="/img/3.png" class="level" />
                    <div>
                        <p class="good">知道分子的时代</p>
                        <p class="jk-num">
                            <span>探花</span>JK0003
                        </p>
                    </div>
                </div>
                <div class="km">149KM</div>
            </div>

        </div>
    </div>

</body>

</html>

<style>
    * {
        margin: 0;
        padding: 0;
    }

    .main {
        background-color: rgb(17, 192, 255);
        width: auto;
        min-height: 100vh;
        overflow: hidden;
        padding: 20px;
        box-sizing: border-box;
        color: #fff;
    }

    .main .title {
        width: 100%;
        text-align: center;
        font-size: 20px;
        font-weight: bold;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .main .title img {
        width: 50px;
        height: 50px;
        margin-right: 10px;
    }

    .main .list-box {
        margin: 25px 0;
        background-color: rgba(255, 255, 255, 0.5);
        border-radius: 16px;
    }

    .main .list-box .list {
        display: flex;
        align-items: center;
        padding: 20px 10px;
        color: #666;
        box-sizing: border-box;
    }

    .main .list-box .list .number {
        width: 25px;
        text-align: left;
        color: #888;
        font-size: 20px;
    }

    .main .list-box .list .user {
        display: flex;
        align-items: center;
        position: relative;
    }

    .main .list-box .list .user .head-url {
        display: block;
        width: 50px;
        height: 50px;
        margin-right: 20px;
        border-radius: 50%;
    }

    .main .list-box .list .user .level {
        display: block;
        position: absolute;
        top: -7.5px;
        left: -7.5px;
        width: 25px;
        z-index: 10;
    }

    .main .list-box .list .user .good {
        font-weight: bold;
    }

    .main .list-box .list .user div {
        min-width: 0;
        flex: 1;


    }

    .main .list-box .list .user div .jk-num {
        margin-top: 10px;
        font-size: 10px;
        color: rgb(190, 190, 190);


    }

    .main .list-box .list .user div span {
        margin-right: 10px;
        color: rgb(252, 13, 13);
    }

    .main .list-box .list .km {
        margin-left: auto;
        font-size: 14px;
    }
</style>