/*
 Navicat Premium Data Transfer

 Source Server         : zb_prod
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 116.63.168.159:3306
 Source Schema         : vendetsms_prod

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 22/07/2020 10:02:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1 , 'admin'    , null, '超管'  , 0, '00', '123@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_user` VALUES (31, 'V00000031', null, '刘庆'  , 0, '00', 'liuqing@vendetech.com', '', '0', '', '$2a$10$A/f7UvGG0CD.TgqNVGP1eON6x1cPf0edWuq9EGFrRuEGhHkGmfI8W', '0', '', NULL, '', '2020-02-03 14:04:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (32, 'V00000051', null, '王晓丹', 0, '01', 'wangxiaodan@vendetech.com', '', '0', '', '$2a$10$yIUjN9/TRkDLg0ggiNhlkOI5ExJi0YKBD1GFdpbW2bbyaYXAJfwhC', '0', '', NULL, '', '2020-02-11 18:19:23', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (33, 'V00000415', null, '卓蓓晓', 0, '01', 'zhuobeixiao@vendetech.com', '', '0', '', '$2a$10$wJGiPQF7SORsT0BSVihQPusO0h1A90WR9kdBpLNhDU1D8Qp5KUymO', '0', '', NULL, '', '2020-02-28 14:23:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (34, 'V00000755', null, '罗丽娜', 1, '01', 'luolina@vendetech.com', '', '0', '', '$2a$10$zaEDIVjIwHRB5RVK74kzouGdKN1cJxm8cVTZIFqzH.3Ew8RydH2xG', '0', '', NULL, '', '2020-02-28 14:23:27', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (35, 'V00000242', null, '林开敏', 1, '01', 'linkaimin@vendetech.com', '', '0', '', '$2a$10$fLiNpnwbir297BFeh/1Tw.GEINLc7tqHHhgCHtu14vQE46Tsq.JE2', '0', '', NULL, '', '2020-02-28 14:23:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (36, 'V00000781', null, '丁斌丽', 0, '01', 'dingbinli@vendetech.com', '', '0', '', '$2a$10$vGWgpz2Yk/.vJ/pPjP4cau6w2SQz0AK.ksPVq7EMezk2Cp.OBOaBO', '0', '', NULL, '', '2020-02-28 14:24:01', 'V00000031', '2020-04-02 17:28:23', NULL);
INSERT INTO `sys_user` VALUES (37, 'V00000148', null, '周晨曦', 3, '01', 'zhouchenxi@vendetech.com', '', '0', '', '$2a$10$tIgDWZldL7FzbH.BG6PcAucf1sYnwJ1548sB5R6gwxfy5ZougeJ8S', '0', '', NULL, '', '2020-02-28 14:24:22', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (38, 'V00000206', null, '吴愉'  , 0, '01', 'wuyu@vendetech.com', '', '0', '', '$2a$10$JrBBngzZCEH/SiE9wSb9UO3GUQZ5hZNtmGQe9shVY7o1TFuiZ7rWC', '0', '', NULL, '', '2020-02-28 14:25:16', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (39, 'V00000805', null, '周之昱', 0, '01', 'zhouzhiyu@vendetech.com', '', '0', '', '$2a$10$37MMALs4IZ2/QDXg4ar8k.aFmq/uoOtsMgMjD7XTKNbkorWxeYTnu', '0', '', NULL, '', '2020-02-28 14:25:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (40, 'V00000097', null, '赵娜'  , 2, '01', 'zhaona@vendetech.com', '', '0', '', '$2a$10$S.5WqBtVheBDXSNMg81oW.6SvRF2TmnQdf14Yalyb5ubFI7S54m/S', '0', '', NULL, '', '2020-02-28 14:25:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (41, 'V00000689', null, '胡凯峰', 0, '01', 'hukaifeng@vendetech.com', '', '0', '', '$2a$10$qpacBUbCPko9S03oOsZS0u70o7elOF/kdBPsgXBobDIP6A9R4mIjC', '0', '', NULL, '', '2020-02-28 14:26:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (42, 'V00000253', null, '王丽'  , 0, '01', 'wangli@vendetech.com', '', '0', '', '$2a$10$AvSjeftnojJD37dyphKWmuD.jdXJlkV6Y.ANY09//KxOMw4p/hCSm', '0', '', NULL, '', '2020-02-28 14:26:21', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (43, 'V00000150', null, '王思'  , 0, '01', 'wangsi@vendetech.com', '', '0', '', '$2a$10$DRK.yVxBVQ57rYPys5dAyeLC/gzjEEYnEeHArd8y1RYtZ95xoy62i', '0', '', NULL, '', '2020-02-28 14:26:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (44, 'V00000078', null, '曹磊'  , 0, '01', 'caolei@vendetech.com', '', '0', '', '$2a$10$qu6p2hIuh2ABDRP4yHMDVuzGsTYNrZ12uUCLtFonUFP6mvQq1TD8m', '0', '', NULL, '', '2020-02-28 14:27:36', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (45, 'V00000157', null, '翟栩'  , 0, '01', 'zhaixu@vendetech.com', '', '0', '', '$2a$10$kHv2HqZkJ9GcQvcq29AHMOPhatOKiVM7nf32VUwGL6fpLBVKYmb4W', '0', '', NULL, '', '2020-02-28 14:28:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (46, 'V00000132', null, '刘飞'  , 0, '01', 'liufei@vendetech.com', '', '0', '', '$2a$10$6opqhBtmOlLOly3YG2iNu.CJNiEGaAW8FTXbgzaS4q1IAAWkiA9hu', '0', '', NULL, '', '2020-02-28 14:28:32', '', '2020-04-08 09:09:17', NULL);
INSERT INTO `sys_user` VALUES (47, 'V00000681', null, '周彬'  , 0, '01', 'zhoubin@vendetech.com', '', '0', '', '$2a$10$4QpaG3QkQpgbE8QH6a6Gi.95D7oMPI96XZa9spetvO5Wqp6rI/nx6', '0', '', NULL, '', '2020-02-28 14:29:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (48, 'V00000670', null, '斯榕斌', 2, '01', 'sirongbin@vendetech.com', '', '0', '', '$2a$10$1oCs7pNt8UN2SiHY.BmawO7/U6MdB4ou3RVdrEZP3NFIcAs9BnL8O', '0', '', NULL, '', '2020-02-28 14:29:18', '', '2020-04-23 17:32:10', NULL);
INSERT INTO `sys_user` VALUES (49, 'V00000429', null, '绪程'  , 0, '01', 'xucheng@vendetech.com', '', '0', '', '$2a$10$T4Wtd.GecLCzFb5Dbn5Hz.LXBScuRqxGaDMcIMuKU3.N/ziowwdMu', '0', '', NULL, '', '2020-02-28 14:29:36', '', '2020-04-08 09:09:39', NULL);
INSERT INTO `sys_user` VALUES (50, 'V00000010', null, '全轶'  , 0, '01', 'quanyi@vendetech.com', '', '0', '', '$2a$10$cTb8Ba8THy63BRpE.AfdzegX86RRURu1Rgv.v6olcZZCZCob16N82', '0', '', NULL, '', '2020-02-28 14:29:51', '', '2020-04-08 09:09:49', NULL);
INSERT INTO `sys_user` VALUES (51, 'V00000024', null, '许二冬', 0, '01', 'xuerdong@vendetech.com', '', '0', '', '$2a$10$0NWhKSKTL9PlVFBftx9PkepeRB07Vjd69woOnVL4L0.LHPP2eiXMa', '0', '', NULL, '', '2020-02-28 14:30:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (52, 'V00000054', null, '谢掾侣', 0, '01', 'xieyuanlv@vendetech.com', '', '0', '', '$2a$10$oPNr4DEZCVANDTWh6uQUyurma1SYjlaLwVc9fVLT.0YpyR0rp6npm', '0', '', NULL, '', '2020-02-28 14:30:28', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (53, 'V00000149', null, '刘慧'  , 0, '01', 'liuhui@vendetech.com', '', '0', '', '$2a$10$2jwh.JroRGrdKD9Rpx0GJOWm0Q.lLUe5QSRcQGztGUR1xmJL02YLe', '0', '', NULL, '', '2020-02-28 14:30:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (54, 'V00000030', null, '何娜'  , 0, '01', 'hena@vendetech.com', '', '0', '', '$2a$10$vdQ.wLFu4MVT0M9GJBmitOyXhzYOKGPzlp6bljQ2ZDtwATB0ta4SW', '0', '', NULL, '', '2020-02-28 14:31:30', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (55, 'V00000020', null, '张妍'  , 0, '01', 'vickyzhang@vendetech.com', '', '0', '', '$2a$10$PmMs7N.3syrlmuH7GMd6i.nJAY0ReJdojV36L6JdlE8Zt2npoNo72', '0', '', NULL, '', '2020-02-28 14:31:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (56, 'V00000255', null, '李晴'  , 0, '01', 'liqing@vendetech.com', '', '0', '', '$2a$10$E8sPH8QiKKgzCW1.iF04muSym.AmDL.nnLYPdHjcx8HfRj3Dd3IHS', '0', '', NULL, '', '2020-02-28 14:32:04', '', '2020-07-01 10:07:52', NULL);
INSERT INTO `sys_user` VALUES (57, 'V00001053', null, '闫振宇', 0, '01', 'yanzhenyu@vendetech.com', '', '0', '', '$2a$10$/7HLSuBX9NnNxuPK3PMLmeS58K/zAL67lTXLFfJ3PdCzATFm0.5cK', '0', '', NULL, '', '2020-02-28 14:32:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (58, 'V00000567', null, '徐舒婷', 0, '01', 'xushuting@vendetech.com', '', '0', '', '$2a$10$LNL8/GqD1WgwF79A5HeHDuwjReM5oFpC8TLKAFHhdTUwSvvPZJxDu', '0', '', NULL, '', '2020-02-28 14:33:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (59, 'V00001117', null, '唐小琳', 0, '01', 'tangxiaolin@vendetech.com', '', '0', '', '$2a$10$guyRBkhaIpmCqqjjNv60gONKXHolQotlWB3lFwkdSfr3BEr.uEmhq', '0', '', NULL, '', '2020-02-28 14:36:34', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (60, 'V00000110', null, '王东青', 0, '01', 'wangdongqing@vendetech.com', '', '0', '', '$2a$10$hiVdYkkvx9RqI01evetv/eA8zV0ozEOP6xtNf7kBeUh39sQV5gHMi', '0', '', NULL, '', '2020-02-28 16:14:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (61, 'V00000556', null, '李娜'  , 0, '01', 'lina@vendetech.com', '', '0', '', '$2a$10$AyXNujUGvIGcM8/8nHi0x.L5exzeQ31IqAb4JxvJBrLUg3sBFZwsa', '0', '', NULL, '', '2020-02-28 16:15:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (62, 'V00000397', null, '贾慧娜', 0, '01', 'tina.jia@vendetech.com', '', '0', '', '$2a$10$kMfpBRx1.lqih8/stFv/I.DNDWMObgTg2eruapyW1IbBW5bXogzFK', '0', '', NULL, '', '2020-04-08 09:06:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (63, 'V00000859', null, '杜金凤', 0, '01', 'dujinfeng@vendetech.com', '', '0', '', '$2a$10$Em9wo1Pm0PYCW8otb7.MOeB/pa3jq6uiSnFBBRKjrXH/rGe2TlTFm', '0', '', NULL, '', '2020-06-22 09:22:49', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
