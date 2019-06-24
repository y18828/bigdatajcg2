package com
import java.sql.Date
import java.text.SimpleDateFormat

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
object tools {

  /**
    * 网站
    * @return
    */
  //访问次数最多的访问
  def max_Web() = {
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)

//    val conf = new SparkConf().setAppName("SparkStreamingAndKafka").setMaster("local[2]")
//    val sc = new StreamingContext(conf, Seconds(5))

    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val file = file_spl.map {
      line => {
        val key = (line(3))
        (key, 1)
      }
    }

    val web = file.reduceByKey((a, b) => a + b).sortBy(_._2, false)
    val max = web.first._2
    val keys=web.filter(x => x._2 >= max).keys.collect()
    var str=""
    for(i <- 0 until keys.length){
      str = str+keys(i)
    }
    sc.stop()
    str
  }

  //根据性别统计访问数最多的网站
  def max_man_web() = {
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }


    val Fdata = file_spl.filter(line => line.contains("男"))
    val Fweb = Fdata.map(line => (line(3), 1)).reduceByKey(_ + _)
    val Fweb1 = Fweb.sortBy(_._2, false)
    val Fmax = Fweb1.first._2
    Fweb.filter(x => x._2 >= Fmax).collect.foreach(println)
    val key_man=Fweb.filter(x => x._2 >= Fmax).keys.collect()
    var str=""
    for(i <- 0 until key_man.length){
      str = str+key_man(i)
    }
    sc.stop()
    str
  }

  def max_woman_web() = {
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"         //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }


    val Fdata = file_spl.filter(line => line.contains("女"))
    val Fweb = Fdata.map(line => (line(3), 1)).reduceByKey(_ + _)
    print(Fdata.map(line => (line(3), 1)))
    val Fweb1 = Fweb.sortBy(_._2, false)
    val Fmax = Fweb1.first._2
    Fweb.filter(x => x._2 >= Fmax).collect.foreach(println)
    val key_woman=Fweb.filter(x => x._2 >= Fmax).keys.collect()
    var str=""
    for(i <- 0 until key_woman.length){
      str = str+key_woman(i)
    }
    sc.stop()
    str
  }


  /**
    * 时间
    * @param start_time
    * @param final_time
    * @return
    */
  def max_time_stu(start_time:String,final_time:String) ={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm").parse(start_time).getTime
        val Date_f=new SimpleDateFormat("HH:mm").parse(final_time).getTime
        Date>=Date_s&&Date<=Date_f
      }
    ).map(x=>(x(0),1))
    val final_data=data1.reduceByKey(_ + _).keys.collect()
    var str=""
    for(i <- 0 until final_data.length){
      str = str+final_data(i)
    }
    sc.stop()
    str
  }

  def max_time_man(start_time:String,final_time:String) ={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm").parse(start_time).getTime
        val Date_f=new SimpleDateFormat("HH:mm").parse(final_time).getTime
        Date>=Date_s&&Date<=Date_f&&x(1)=="男"
      }
    ).map(x=>(x(0),1))
    val final_data=data1.reduceByKey(_ + _).keys.collect()
    var str=""
    for(i <- 0 until final_data.length){
      str = str+final_data(i)
    }
    sc.stop()
    str
  }

  def max_time_woman(start_time:String,final_time:String) ={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm").parse(start_time).getTime
        val Date_f=new SimpleDateFormat("HH:mm").parse(final_time).getTime
        Date>=Date_s&&Date<=Date_f&&x(1)=="女"
      }
    ).map(x=>(x(0),1))
    val final_data=data1.reduceByKey(_ + _).keys.collect()
    var str=""
    for(i <- 0 until final_data.length){
      str = str+final_data(i)
    }
    sc.stop()
    str
  }



  def _7(time:Int)={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val data1=file_spl.map(
      x=>{
        val lim=time*60*1000
        val Date = new SimpleDateFormat("MM/dd HH:mm:ss").parse(x(4)).getTime
        var low=Date-lim
        var tmp=low%lim
        low=Date-tmp
        val lowtime :String = new SimpleDateFormat("HH:mm").format(low)
        val hightime:String = new SimpleDateFormat("HH:mm").format(low+lim-60000)
        val final_time=lowtime+"-"+hightime
        (final_time,1)
      }
    ).reduceByKey(_+_).sortBy(_._2, false)
    val final_data=data1.collect()
    var str=""
    for(i <- 0 until final_data.length){
      str = str+final_data(i)
    }
    sc.stop()
    str
  }


  def _8(start_time:String,final_time:String)={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }

    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm").parse(start_time).getTime
        val Date_f=new SimpleDateFormat("HH:mm").parse(final_time).getTime
        Date>=Date_s&&Date<=Date_f
      }
    ).map(x=>(x(2),1))
    val final_data=data1.reduceByKey(_ + _).collect()
    var str=""
    for(i <- 0 until final_data.length){
      str = str+final_data(i)+" "
    }
    sc.stop()
    str
  }



  def _9(web:String,time:String)={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }


    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm:ss").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm:ss").parse(time.split("-")(0)).getTime
        val Date_f=new SimpleDateFormat("HH:mm:ss").parse(time.split("-")(1)).getTime
        Date>=Date_s&&Date<=Date_f
      }
    ).filter(x=>x.contains(web)).filter(x=>x.contains("男"))
    val final_data=data1.collect()
    var str=""
    for(i <- 0 until final_data.length){
      for(j <-0 until final_data(i).length){
        str=str+final_data(i)(j)+" "
      }
    }
    sc.stop()
    str
  }



  def _10(web:String,time:String)={
    System.setProperty("hadoop.home.dir", "E:\\hadoopjars\\hadoop-2.6.0")
    val conf = new SparkConf().setAppName("jcg").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputFile = "D:\\Software\\IDEA\\AllProject\\bigdatajcg2\\data.txt"        //C:\Users\WJC\Desktop\JCG\jcg.txt      file:///usr/jcg.txt
    val textFile = sc.textFile(inputFile)
    val file_spl = textFile.map { line => line.split("-") }


    val data=file_spl.map(
      line=>{
        val x=line(4).split(" ")
        Array(line(0),line(1),line(2),line(3),x(0),x(1))
      }
    )
    val data1=data.filter(
      x=>{
        val Date = new SimpleDateFormat("HH:mm:ss").parse(x(5)).getTime
        val Date_s=new SimpleDateFormat("HH:mm:ss").parse(time.split("-")(0)).getTime
        val Date_f=new SimpleDateFormat("HH:mm:ss").parse(time.split("-")(1)).getTime
        Date>=Date_s&&Date<=Date_f
      }
    ).filter(x=>x.contains(web)).filter(x=>x.contains("女"))
    val final_data=data1.collect()
    var str=""
    for(i <- 0 until final_data.length){
      for(j <-0 until final_data(i).length){
        str=str+final_data(i)(j)+" "
      }
    }
    sc.stop()
    str
  }






}
