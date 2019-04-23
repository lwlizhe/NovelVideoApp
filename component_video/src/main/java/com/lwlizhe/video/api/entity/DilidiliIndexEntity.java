package com.lwlizhe.video.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2019/2/14.
 */

public class DilidiliIndexEntity extends DilidiliBaseData{

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_WEEK = 1;
    public static final int TYPE_RECENT = 2;
    public static final int TYPE_RECOMMEND = 3;

    /**
     * errorCode : 0
     * message :
     * version : 20190214
     * data : {"carousel":[{"id":1,"name":"与世界为敌，他最终能得到什么呢！？","imgUrl":"http://app.dilidili.club/storage/ad/20190129/1.jpg","resource":{"resLocation":"4593","resType":3}},{"id":2,"name":"逃亡的终点会在哪里？","imgUrl":"http://app.dilidili.club/storage/ad/20190129/22.jpg","resource":{"resLocation":"4560","resType":3}},{"id":3,"name":"名为\u201c百鬼丸\u201d的男人。那究竟是鬼，还是人？\r\n名为\u201c百鬼丸\u201d的男人。那究竟是鬼，还是人？\r\n名为\u201c百鬼丸\u201d的男人。那究竟是鬼，还是人？\r\n名为\u201c百鬼丸\u201d的男人。那究竟是鬼，还是人？\r\n名为\u201c百鬼丸\u201d的男人。那究竟是鬼，还是人？","imgUrl":"http://app.dilidili.club/storage/ad/20190129/3.jpg","resource":{"resLocation":"4575","resType":3}},{"id":4,"name":"青春期少年的灵魂在咆哮：龙套回来了！","imgUrl":"http://app.dilidili.club/storage/ad/20190129/4.jpg","resource":{"resLocation":"4648","resType":3}}],"weekList":[[{"id":3127,"name":"叫我僵小鱼 第二季","updatedEpisode":"56","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/290_1928492502.jpg","resource":{"resLocation":3127,"resType":3}},{"id":4225,"name":"弦音-风舞高中弓道部","updatedEpisode":"13","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0453491161.jpg","resource":{"resLocation":4225,"resType":3}},{"id":4367,"name":"圣斗士星矢 圣斗少女翔","updatedEpisode":"9","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0216053051.jpg","resource":{"resLocation":4367,"resType":3}},{"id":4369,"name":"苍天之拳REGENESIS 第2期","updatedEpisode":"12","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0246176681.jpg","resource":{"resLocation":4369,"resType":3}},{"id":4575,"name":"多罗罗","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1423245511.jpg","resource":{"resLocation":4575,"resType":3}},{"id":4580,"name":"钢琴之森 第2期","updatedEpisode":"3","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1527066591.jpg","resource":{"resLocation":4580,"resType":3}},{"id":4587,"name":"笨拙之极的上野","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1646285911.jpg","resource":{"resLocation":4587,"resType":3}},{"id":4609,"name":"粉彩回忆","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181203/296_1621131771.jpg","resource":{"resLocation":4609,"resType":3}},{"id":4648,"name":"灵能百分百 第二季","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181218/299_1104153031.jpg","resource":{"resLocation":4648,"resType":3}},{"id":4678,"name":"PSO2乱炖","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/190121/298_1739163171.jpg","resource":{"resLocation":4678,"resType":3}}],[{"id":2817,"name":"黑色五叶草","updatedEpisode":"70","imgUrl":"http://www.dilidili.name/uploads/allimg/181113/296_1524386841.jpg","resource":{"resLocation":2817,"resType":3}},{"id":3141,"name":"雷顿神秘侦探社～卡特莉的解谜事件簿～","updatedEpisode":"40","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/290_2139237691.jpg","resource":{"resLocation":3141,"resType":3}},{"id":3172,"name":"足球小将翼","updatedEpisode":"45","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/290_2033341694.jpg","resource":{"resLocation":3172,"resType":3}},{"id":4217,"name":"关于我转生变成史莱姆这档事","updatedEpisode":"19","imgUrl":"http://www.dilidili.name/uploads/allimg/181010/296_1805008651.jpg","resource":{"resLocation":4217,"resType":3}},{"id":4289,"name":"镜光传说 小剧场","updatedEpisode":"1","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1847225761.jpg","resource":{"resLocation":4289,"resType":3}},{"id":4415,"name":"能继续奔跑下去真是太好了。","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0234361231.jpg","resource":{"resLocation":4415,"resType":3}},{"id":4439,"name":"爆钓BARHUNTER","updatedEpisode":"19","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0221155651.jpg","resource":{"resLocation":4439,"resType":3}},{"id":4559,"name":"天使降临到我身边","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181116/296_0959506801.jpg","resource":{"resLocation":4559,"resType":3}},{"id":4572,"name":"兽娘动物园2","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1358414821.jpg","resource":{"resLocation":4572,"resType":3}},{"id":4654,"name":"CIRCLET PRINCESS","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1654138271.jpg","resource":{"resLocation":4654,"resType":3}}],[{"id":3242,"name":"战斗陀螺BURST 超绝","updatedEpisode":"19","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1830335841.jpg","resource":{"resLocation":3242,"resType":3}},{"id":4216,"name":"强风吹拂","updatedEpisode":"17","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0504352721.jpg","resource":{"resLocation":4216,"resType":3}},{"id":4570,"name":"雨色可可side G","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1336481501.jpg","resource":{"resLocation":4570,"resType":3}},{"id":4583,"name":"烟草 Kemurikusa","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/298_1113516501.jpg","resource":{"resLocation":4583,"resType":3}},{"id":4584,"name":"狂赌之渊××","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1620597241.jpg","resource":{"resLocation":4584,"resType":3}},{"id":4588,"name":"临死！！江古田","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1655255861.jpg","resource":{"resLocation":4588,"resType":3}},{"id":4592,"name":"3D彼女 第2期","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1724112231.jpg","resource":{"resLocation":4592,"resType":3}},{"id":4593,"name":"盾之勇者成名录","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1730439551.jpg","resource":{"resLocation":4593,"resType":3}},{"id":4652,"name":"明治东京恋伽","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/190203/296_1417337231.jpg","resource":{"resLocation":4652,"resType":3}},{"id":4657,"name":"虚拟小姐在看着你","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/190203/296_1412598041.jpg","resource":{"resLocation":4657,"resType":3}},{"id":4659,"name":"正中心的陆君","updatedEpisode":null,"imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1734501501.jpg","resource":{"resLocation":4659,"resType":3}}],[{"id":3370,"name":"未来卡 神之搭档对战","updatedEpisode":"24","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1836476931.jpg","resource":{"resLocation":3370,"resType":3}},{"id":4204,"name":"魔偶马戏团","updatedEpisode":"16","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0311075831.jpg","resource":{"resLocation":4204,"resType":3}},{"id":4564,"name":"松松小镇","updatedEpisode":"25","imgUrl":"http://www.dilidili.name/uploads/allimg/181119/296_1654229321.jpg","resource":{"resLocation":4564,"resType":3}},{"id":4577,"name":"同居人时而在腿上，时而跑到脑袋上。","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1438036901.jpg","resource":{"resLocation":4577,"resType":3}},{"id":4589,"name":"BanG Dream！2nd Season","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1704144671.jpg","resource":{"resLocation":4589,"resType":3}},{"id":4594,"name":"revisions","updatedEpisode":"12（全集）","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1737083921.jpg","resource":{"resLocation":4594,"resType":3}},{"id":4641,"name":"Dimension High School","updatedEpisode":"PV","imgUrl":"http://www.dilidili.name/uploads/allimg/181213/296_1605583251.jpg","resource":{"resLocation":4641,"resType":3}},{"id":4644,"name":"尽量加油吧！魔法少女胡桃 第二季","updatedEpisode":"6（生肉）","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1849154621.jpg","resource":{"resLocation":4644,"resType":3}},{"id":4661,"name":"交锋联盟之机巧一族","updatedEpisode":"PV","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1756141511.jpg","resource":{"resLocation":4661,"resType":3}},{"id":4663,"name":"第四真祖","updatedEpisode":"1+2","imgUrl":"http://www.dilidili.name/uploads/allimg/181207/296_1512556031.jpg","resource":{"resLocation":4663,"resType":3}}],[{"id":3259,"name":"卡片战斗先导者 2018","updatedEpisode":"37","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1906035951.jpg","resource":{"resLocation":3259,"resType":3}},{"id":4121,"name":"魔法禁书目录第三季","updatedEpisode":"18","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0316285511.jpg","resource":{"resLocation":4121,"resType":3}},{"id":4259,"name":"火之丸相扑","updatedEpisode":"17","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0201596761.jpg","resource":{"resLocation":4259,"resType":3}},{"id":4417,"name":"Devidol！","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0227029471.jpg","resource":{"resLocation":4417,"resType":3}},{"id":4459,"name":"剑网3·侠肝义胆沈剑心","updatedEpisode":"2","imgUrl":"http://www.dilidili.name/uploads/allimg/180924/300_2038558421.jpg","resource":{"resLocation":4459,"resType":3}},{"id":4468,"name":"闪电十一人 猎户座的刻印","updatedEpisode":"pv","imgUrl":"http://www.dilidili.name/uploads/allimg/180927/296_1759415281.jpg","resource":{"resLocation":4468,"resType":3}},{"id":4479,"name":"FLCL Alternative","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181001/296_1849427541.jpg","resource":{"resLocation":4479,"resType":3}},{"id":4560,"name":"约定的梦幻岛","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181116/296_1052022821.jpg","resource":{"resLocation":4560,"resType":3}},{"id":4576,"name":"约会大作战Ⅲ","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1431135341.jpg","resource":{"resLocation":4576,"resType":3}},{"id":4581,"name":"Flying Babies","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1533377341.jpg","resource":{"resLocation":4581,"resType":3}},{"id":4582,"name":"不吉波普不笑","updatedEpisode":"7","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1539347071.jpg","resource":{"resLocation":4582,"resType":3}},{"id":4614,"name":"格林笔记 The Animation","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181203/296_1658461171.jpg","resource":{"resLocation":4614,"resType":3}},{"id":4632,"name":"笑容的代价","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181206/296_1418513131.jpg","resource":{"resLocation":4632,"resType":3}},{"id":4656,"name":"五等分的花嫁","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1713123661.jpg","resource":{"resLocation":4656,"resType":3}},{"id":4683,"name":"餐桌上的世说新语","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/190201/300_1916004031.jpg","resource":{"resLocation":4683,"resType":3}}],[{"id":2768,"name":"游戏王VRAINS","updatedEpisode":"88","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/6_1914195195.jpg","resource":{"resLocation":2768,"resType":3}},{"id":2791,"name":"宇宙战舰大和号2202 爱的战士们","updatedEpisode":"第七章特报","imgUrl":"http://www.dilidili.name/uploads/allimg/181012/296_1748197661.jpg","resource":{"resLocation":2791,"resType":3}},{"id":3330,"name":"ZOIDS WILD","updatedEpisode":"31","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/295_1859006771.jpg","resource":{"resLocation":3330,"resType":3}},{"id":4207,"name":"欢迎光临千岁酱","updatedEpisode":"18","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0307068331.jpg","resource":{"resLocation":4207,"resType":3}},{"id":4213,"name":"逆转裁判第二季","updatedEpisode":"16","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0302371461.jpg","resource":{"resLocation":4213,"resType":3}},{"id":4224,"name":"JOJO的奇妙冒险 黄金之风","updatedEpisode":"18","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0454568571.jpg","resource":{"resLocation":4224,"resType":3}},{"id":4229,"name":"Radiant","updatedEpisode":"19","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0448096921.jpg","resource":{"resLocation":4229,"resType":3}},{"id":4398,"name":"闪亮幸福 开启吧！","updatedEpisode":"20","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1845096891.jpg","resource":{"resLocation":4398,"resType":3}},{"id":4566,"name":"多彩田园曲 ～来自百慕大三角△～","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/181121/296_1408459881.jpg","resource":{"resLocation":4566,"resType":3}},{"id":4573,"name":"不愉快的怪物庵 续","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1410203351.jpg","resource":{"resLocation":4573,"resType":3}},{"id":4578,"name":"家有女友","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1444269621.jpg","resource":{"resLocation":4578,"resType":3}},{"id":4585,"name":"飞翔吧！战机少女","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1629314451.jpg","resource":{"resLocation":4585,"resType":3}},{"id":4586,"name":"B-PROJECT～绝顶*Emotion～","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1637529231.jpg","resource":{"resLocation":4586,"resType":3}},{"id":4590,"name":"魔法少女特殊战明日香","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1710406711.jpg","resource":{"resLocation":4590,"resType":3}}],[{"id":2629,"name":"宠物小精灵 太阳/月亮","updatedEpisode":"107","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/290_2018239571.jpg","resource":{"resLocation":2629,"resType":3}},{"id":2728,"name":"博人传","updatedEpisode":"93","imgUrl":"http://www.dilidili.name/uploads/allimg/180419/290_2051098131.jpg","resource":{"resLocation":2728,"resType":3}},{"id":3338,"name":"鬼太郎 第6期","updatedEpisode":"42","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/295_1821493461.jpg","resource":{"resLocation":3338,"resType":3}},{"id":4127,"name":"刀剑神域 Alicization","updatedEpisode":"18","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0314577901.jpg","resource":{"resLocation":4127,"resType":3}},{"id":4208,"name":"叛逆性百万亚瑟王","updatedEpisode":"10","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0305439511.jpg","resource":{"resLocation":4208,"resType":3}},{"id":4264,"name":"妖精的尾巴 最终章","updatedEpisode":"295","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0158206691.jpg","resource":{"resLocation":4264,"resType":3}},{"id":4382,"name":"假面骑士时王","updatedEpisode":"21","imgUrl":"http://www.dilidili.name/uploads/allimg/180917/296_1844037201.jpg","resource":{"resLocation":4382,"resType":3}},{"id":4438,"name":"《GIFT±》","updatedEpisode":"04","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0223527681.jpg","resource":{"resLocation":4438,"resType":3}},{"id":4444,"name":"麟&犀AI韵律2","updatedEpisode":"15","imgUrl":"http://www.dilidili.name/uploads/allimg/180922/302_0145394611.jpg","resource":{"resLocation":4444,"resType":3}},{"id":4467,"name":"爱玩怪兽","updatedEpisode":"18","imgUrl":"http://www.dilidili.name/uploads/allimg/180927/296_1441364501.jpg","resource":{"resLocation":4467,"resType":3}},{"id":4565,"name":"ENDRO~!","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181121/296_1357261991.jpg","resource":{"resLocation":4565,"resType":3}},{"id":4574,"name":"W\u2019z","updatedEpisode":"6","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1416354631.jpg","resource":{"resLocation":4574,"resType":3}},{"id":4579,"name":"巴哈姆特之怒-Manaria Friends-","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1515301861.jpg","resource":{"resLocation":4579,"resType":3}},{"id":4591,"name":"辉夜姬想让人告白～天才们的恋爱头脑战～","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181123/296_1716121731.jpg","resource":{"resLocation":4591,"resType":3}},{"id":4634,"name":"荒野的KOTOBUKI飞行队","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181207/296_1556214701.jpg","resource":{"resLocation":4634,"resType":3}},{"id":4655,"name":"迷你刀使","updatedEpisode":"4","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1704364651.jpg","resource":{"resLocation":4655,"resType":3}},{"id":4658,"name":"就算是爸爸也想做","updatedEpisode":"5","imgUrl":"http://www.dilidili.name/uploads/allimg/181226/299_1726473471.jpg","resource":{"resLocation":4658,"resType":3}},{"id":4664,"name":"监狱实验","updatedEpisode":"04","imgUrl":"http://www.dilidili.name/uploads/allimg/181229/299_1506506301.jpg","resource":{"resLocation":4664,"resType":3}}]],"editorPick":[{"id":77737,"name":"同居人时而在腿上，时而跑到脑袋上。 第6话 连系之物","episode":"6","writer":"6","isUgc":0,"description":"电视动画《同居人时而在腿上，时而跑到脑袋上。》改编自みなつき、二ツ家あす创作的同名漫画作品，于2018年8月8日宣布电视动画化。","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2349257501.jpg","resource":{"resLocation":77737,"resType":4}},{"id":77736,"name":"明治东京恋伽 第6话 梦与热情的电能","episode":"6","writer":"6","isUgc":0,"description":"手游《明治东京恋伽》即将被改编为真人版电影、电视剧以及TV动画。《明治东京恋伽》是于2013年推出的手机游戏，随后又登陆了PSP、PSV平台，曾推出过两部剧场版动画电影。","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2254204111.jpg","resource":{"resLocation":77736,"resType":4}},{"id":77730,"name":"盾之勇者成名录 第6话 新同伴","episode":"6","writer":"6","isUgc":0,"description":"岩谷尚文是一名 20 岁的大二学生，在图书馆无意间发现了一本《四圣武器书》结果被召唤到了一个异世界当中，还莫名其妙的就成为了\u201c盾之勇者\u201d，但是在这个世界中盾被认为是没什么用的武器。而后还遭人陷害被诬陷为强暴犯。","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2156366721.jpg","resource":{"resLocation":77730,"resType":4}},{"id":77729,"name":"烟草 第6话","episode":"6","writer":"6","isUgc":0,"description":"作品背景为末日废土世界，以被红色的雾所覆盖的世界为舞台，讲述与被称作\u201c虫\u201d的异形怪物战斗的少女们的故事。于2019年1月播出","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2129442431.jpg","resource":{"resLocation":77729,"resType":4}}],"ugcPick":[{"id":77735,"name":"用电脑壁纸的方式打开辉月大小姐ED时","episode":"勇士心上一把剑","writer":"勇士心上一把剑","description":"用电脑壁纸的方式打开辉月大小姐ED时","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2240317261.jpg","resource":{"resLocation":77735,"resType":4}},{"id":77734,"name":"有里知花 - 涙の物语","episode":"勇士心上一把剑","writer":"勇士心上一把剑","description":"有里知花 - 涙の物语 带耳机食用效果更好哦！","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2236334971.jpg","resource":{"resLocation":77734,"resType":4}},{"id":77733,"name":"谁是友军？谁是凶手？吐槽柯南宇宙最强无间道片 柯南队长20 内战！","episode":"小尼meow","writer":"小尼meow","description":"B站and微博ID： 小尼meow 一起来玩吧~","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2231224901.jpg","resource":{"resLocation":77733,"resType":4}},{"id":77732,"name":"【内含独特字评】量子破碎评测\u2014\u2014电影游戏化的枢纽","episode":"鳰姬Piont","writer":"鳰姬Piont","description":"迟来的量子破碎测评，由于嗓子出了问题后期声音崩毁了，没有字幕真的是抱歉啦QTZ，希望我说的足够清楚，希望喜欢的人可以蟹哥评论鼓励我，为什么？因为我的名字很奇怪啦！","updatedAt":"2019-02-13","imgUrl":"http://www.dilidili.name/uploads/allimg/190213/302_2227112461.jpg","resource":{"resLocation":77732,"resType":4}}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CarouselBean> carousel;
        private List<List<WeekListBean>> weekList;
        private List<EditorPickBean> editorPick;
        private List<UgcPickBean> ugcPick;

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<List<WeekListBean>> getWeekList() {
            return weekList;
        }

        public void setWeekList(List<List<WeekListBean>> weekList) {
            this.weekList = weekList;
        }

        public List<EditorPickBean> getEditorPick() {
            return editorPick;
        }

        public void setEditorPick(List<EditorPickBean> editorPick) {
            this.editorPick = editorPick;
        }

        public List<UgcPickBean> getUgcPick() {
            return ugcPick;
        }

        public void setUgcPick(List<UgcPickBean> ugcPick) {
            this.ugcPick = ugcPick;
        }

        public static class CarouselBean extends BaseMultiItemData {
            /**
             * id : 1
             * name : 与世界为敌，他最终能得到什么呢！？
             * imgUrl : http://app.dilidili.club/storage/ad/20190129/1.jpg
             * resource : {"resLocation":"4593","resType":3}
             */

            private int id;
            private String name;
            private String imgUrl;
            private ResourceBean resource;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public ResourceBean getResource() {
                return resource;
            }

            public void setResource(ResourceBean resource) {
                this.resource = resource;
            }

            @Override
            public int getType() {
                return 0;
            }

            public static class ResourceBean {
                /**
                 * resLocation : 4593
                 * resType : 3
                 */

                private String resLocation;
                private int resType;

                public String getResLocation() {
                    return resLocation;
                }

                public void setResLocation(String resLocation) {
                    this.resLocation = resLocation;
                }

                public int getResType() {
                    return resType;
                }

                public void setResType(int resType) {
                    this.resType = resType;
                }
            }
        }

        public static class WeekListBean extends BaseMultiItemData{
            /**
             * id : 3127
             * name : 叫我僵小鱼 第二季
             * updatedEpisode : 56
             * imgUrl : http://www.dilidili.name/uploads/allimg/180419/290_1928492502.jpg
             * resource : {"resLocation":3127,"resType":3}
             */

            private int id;
            private String name;
            private String updatedEpisode;
            private String imgUrl;
            private ResourceBeanX resource;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUpdatedEpisode() {
                return updatedEpisode;
            }

            public void setUpdatedEpisode(String updatedEpisode) {
                this.updatedEpisode = updatedEpisode;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public ResourceBeanX getResource() {
                return resource;
            }

            public void setResource(ResourceBeanX resource) {
                this.resource = resource;
            }

            @Override
            public int getType() {
                return 0;
            }

            public static class ResourceBeanX {
                /**
                 * resLocation : 3127
                 * resType : 3
                 */

                private int resLocation;
                private int resType;

                public int getResLocation() {
                    return resLocation;
                }

                public void setResLocation(int resLocation) {
                    this.resLocation = resLocation;
                }

                public int getResType() {
                    return resType;
                }

                public void setResType(int resType) {
                    this.resType = resType;
                }
            }
        }

        public static class EditorPickBean extends BaseMultiItemData{
            /**
             * id : 77737
             * name : 同居人时而在腿上，时而跑到脑袋上。 第6话 连系之物
             * episode : 6
             * writer : 6
             * isUgc : 0
             * description : 电视动画《同居人时而在腿上，时而跑到脑袋上。》改编自みなつき、二ツ家あす创作的同名漫画作品，于2018年8月8日宣布电视动画化。
             * updatedAt : 2019-02-13
             * imgUrl : http://www.dilidili.name/uploads/allimg/190213/302_2349257501.jpg
             * resource : {"resLocation":77737,"resType":4}
             */

            private int id;
            private String name;
            private String episode;
            private String writer;
            private int isUgc;
            private String description;
            private String updatedAt;
            private String imgUrl;
            private ResourceBeanXX resource;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEpisode() {
                return episode;
            }

            public void setEpisode(String episode) {
                this.episode = episode;
            }

            public String getWriter() {
                return writer;
            }

            public void setWriter(String writer) {
                this.writer = writer;
            }

            public int getIsUgc() {
                return isUgc;
            }

            public void setIsUgc(int isUgc) {
                this.isUgc = isUgc;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public ResourceBeanXX getResource() {
                return resource;
            }

            public void setResource(ResourceBeanXX resource) {
                this.resource = resource;
            }

            @Override
            public int getType() {
                return 0;
            }

            public static class ResourceBeanXX {
                /**
                 * resLocation : 77737
                 * resType : 4
                 */

                private int resLocation;
                private int resType;

                public int getResLocation() {
                    return resLocation;
                }

                public void setResLocation(int resLocation) {
                    this.resLocation = resLocation;
                }

                public int getResType() {
                    return resType;
                }

                public void setResType(int resType) {
                    this.resType = resType;
                }
            }
        }

        public static class UgcPickBean extends BaseMultiItemData{
            /**
             * id : 77735
             * name : 用电脑壁纸的方式打开辉月大小姐ED时
             * episode : 勇士心上一把剑
             * writer : 勇士心上一把剑
             * description : 用电脑壁纸的方式打开辉月大小姐ED时
             * updatedAt : 2019-02-13
             * imgUrl : http://www.dilidili.name/uploads/allimg/190213/302_2240317261.jpg
             * resource : {"resLocation":77735,"resType":4}
             */

            private int id;
            private String name;
            private String episode;
            private String writer;
            private String description;
            private String updatedAt;
            private String imgUrl;
            private ResourceBeanXXX resource;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEpisode() {
                return episode;
            }

            public void setEpisode(String episode) {
                this.episode = episode;
            }

            public String getWriter() {
                return writer;
            }

            public void setWriter(String writer) {
                this.writer = writer;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public ResourceBeanXXX getResource() {
                return resource;
            }

            public void setResource(ResourceBeanXXX resource) {
                this.resource = resource;
            }

            @Override
            public int getType() {
                return 0;
            }

            public static class ResourceBeanXXX {
                /**
                 * resLocation : 77735
                 * resType : 4
                 */

                private int resLocation;
                private int resType;

                public int getResLocation() {
                    return resLocation;
                }

                public void setResLocation(int resLocation) {
                    this.resLocation = resLocation;
                }

                public int getResType() {
                    return resType;
                }

                public void setResType(int resType) {
                    this.resType = resType;
                }
            }
        }
    }
}
