/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package sample;


import java.awt.List;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 * IKAnalyzer 示例
 * 2012-3-2
 * 
 * 以下是结合Lucene3.4 API的写法
 *
 */
public class IKAnalyzerDemo {
	
		public static void main(String[] args)
		{
			IKSegmenter seg=null;
			ArrayList<String> list=new ArrayList<String>();
			list.add("苏轼不是圣人，他最可贵的地方是在痛苦彷徨挣扎中，" +
					"总能把自己的心灵置于更广阔的天地中，如同《赤壁后赋》中横飞而去的老鹤，" +
					"戛戛于星空夜月，长河大江之上，澄明清澈，皎然不滓。" +
					"苏轼是一个善于苦中找乐的人，这种乐观与真趣帮他度过了不少难关。" +
					"画家陈丹青说鲁迅是一个有趣的人，我想，拿来说苏东坡一样也行。");
			list.add("李宇春《再不疯狂我们就老了》MV首播】李宇春新专辑同名第二主打《再不疯狂我们就老了》MV今日正式发布。这首歌与《似火年华》，以“疯狂”为概念的对话曲目，采用一曲双词的方式。李宇春与韩寒，同时在一首歌里，讲述了两种截然相反，却本质同归的态度");
			list.add("上个月在天津术语学会上见到冯老，言谈中感觉到冯老对机器翻译的深厚感情和殷切希望。是啊，机器翻译事业还年轻，我辈细流，心驰沧海，愿倾尽绵薄之力，浇灌此常青之树。");
			list.add("发表了博文 《多语言信息网络时代的语言学家：冯志伟》 - 冯志伟与老伴郑初阳 多语言信息网络时代的语言学家：冯志伟 桂清扬 冯志伟，教育部语言文字应用研究所研究员，博士生导师，所学术委员会");
			list.add("Facebook CEO 马克·扎克伯格亮相了周二 TechCrunch Disrupt 大会，并针对公司不断下挫的股价、移动战略、广告业务等方面发表了讲话。自 5 月公司 IPO 后，扎克伯格极少公开露面，这也是他首次在重要场合公开接受采访");
			list.add("@新华社中国网事：#聚焦钓鱼岛#外交部长杨洁篪10日在外交部紧急召见日本驻华大使丹羽宇一郎，就日本政府非法“购买”钓鱼岛提出严正交涉和强烈抗议。当日，中国驻日本大使程永华也向日本外务省负责人提出严正交涉并递交了抗议照会。");
			list.add("阿米尔汗，8岁时出演一部轰动印度的电影，是公认的童星，长大后却一心打网球并获得过网球冠军。21岁爱上邻居家女孩，由于宗教原因两人决定私奔，现在过着幸福美满的生活。81届奥斯卡最佳影片《贫民窟的百万富翁》，他担任制片。2009年一部《三个白痴》震惊全球，他47岁");
			list.add("老郭动粗 师徒揭相声虚假繁荣");
			list.add("Facebook CEO 扎克伯格极少公开露面");
			list.add("徐德有说这是个错误!");
			list.add("而如今Facebook的CEO马克·扎克伯格表示，押在HTML5上是Facebook最大的错误。由于HTML5应用性能差到不能忍受");
			list.add("本报讯（记者胡笑红）已经过期的牛奶被销售经理修改日期,照样投放市场销售，记者昨天从蒙牛公司得到证实，蒙牛驻义乌经理王孙富和同伙赵宝峰因涉嫌生产销售伪劣产品罪已被当地批捕。");
			list.add("白玉萍是一个好人");
			list.add("张三同李四是好朋友");
			list.add("钟子期的名字能够被认出来么");
			list.add("綦玉冰");
			list.add("汤姆克鲁斯的英文名字很苦");
			list.add("曼城第23分钟遭遇打击，孔帕尼中线丢球，莫里森中路直塞，沙恩-朗拿球成单刀之势，米尔纳背后将其铲倒，主裁判克拉滕伯格认为米尔纳是最后一名防守球员，直接掏出红牌！曼奇尼在场边向第四官员抗议，认为莱斯科特已经补防到位。多兰斯主罚任意球打在人墙上高出。");
			list.add("中新网10月20日电 据日本共同社报道，日本民主党代理干事长安住淳20日表示，首相野田佳彦将履行“近期”解散众院举行大选的承诺，预计在“公债发行特例法案”获得通过等条件具备时解散众院。");
			for (String string : list) {
				StringReader reader=new StringReader(string);
				seg=new IKSegmenter(reader,true);
				Lexeme lex=new Lexeme(0, 0, 0, 0);
				try {
					while((lex=seg.next())!=null)
					{
						System.out.print(lex.getLexemeText() + " ");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			}
			
		}
		

}