package com.blueweather.app.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2016/3/3.
 */
public class Weather implements Serializable {

    /**
     * city : {"aqi":"99","co":"1","no2":"87","o3":"36","pm10":"106","pm25":"74","qlty":"��","so2":"16"}
     */

    @SerializedName("aqi")
    public AqiEntity aqi;
    /**
     * city : ����
     * cnty : �й�
     * id : CN101040100
     * lat : 29.581000
     * lon : 106.549000
     * update : {"loc":"2016-02-18 21:04","utc":"2016-02-18 13:04"}
     */

    @SerializedName("basic")
    public BasicEntity basic;
    /**
     * cond : {"code":"101","txt":"����"}
     * fl : 10
     * hum : 57
     * pcpn : 0
     * pres : 1019
     * tmp : 11
     * vis : 10
     * wind : {"deg":"20","dir":"������","sc":"4-5","spd":"17"}
     */

    @SerializedName("now")
    public NowEntity now;
    /**
     * aqi : {"city":{"aqi":"99","co":"1","no2":"87","o3":"36","pm10":"106","pm25":"74","qlty":"��","so2":"16"}}
     * basic : {"city":"����","cnty":"�й�","id":"CN101040100","lat":"29.581000","lon":"106.549000","update":{"loc":"2016-02-18
     * 21:04","utc":"2016-02-18 13:04"}}
     * daily_forecast : [{"astro":{"sr":"07:30","ss":"18:44"},"cond":{"code_d":"100","code_n":"104","txt_d":"��","txt_n":"��"},"date":"2016-02-18","hum":"38","pcpn":"0.0","pop":"0","pres":"1019","tmp":{"max":"19","min":"7"},"vis":"10","wind":{"deg":"54","dir":"�޳�������","sc":"΢��","spd":"6"}},{"astro":{"sr":"07:29","ss":"18:45"},"cond":{"code_d":"104","code_n":"104","txt_d":"��","txt_n":"��"},"date":"2016-02-19","hum":"54","pcpn":"0.7","pop":"31","pres":"1027","tmp":{"max":"13","min":"7"},"vis":"2","wind":{"deg":"204","dir":"�޳�������","sc":"΢��","spd":"1"}},{"astro":{"sr":"07:29","ss":"18:46"},"cond":{"code_d":"104","code_n":"104","txt_d":"��","txt_n":"��"},"date":"2016-02-20","hum":"56","pcpn":"1.6","pop":"61","pres":"1027","tmp":{"max":"12","min":"7"},"vis":"10","wind":{"deg":"141","dir":"�޳�������","sc":"΢��","spd":"7"}},{"astro":{"sr":"07:28","ss":"18:46"},"cond":{"code_d":"305","code_n":"305","txt_d":"С��","txt_n":"С��"},"date":"2016-02-21","hum":"73","pcpn":"5.9","pop":"72","pres":"1020","tmp":{"max":"10","min":"6"},"vis":"2","wind":{"deg":"48","dir":"�޳�������","sc":"΢��","spd":"3"}},{"astro":{"sr":"07:27","ss":"18:47"},"cond":{"code_d":"104","code_n":"104","txt_d":"��","txt_n":"��"},"date":"2016-02-22","hum":"60","pcpn":"1.0","pop":"67","pres":"1025","tmp":{"max":"10","min":"7"},"vis":"10","wind":{"deg":"95","dir":"�޳�������","sc":"΢��","spd":"10"}},{"astro":{"sr":"07:26","ss":"18:48"},"cond":{"code_d":"305","code_n":"104","txt_d":"С��","txt_n":"��"},"date":"2016-02-23","hum":"74","pcpn":"6.5","pop":"58","pres":"1027","tmp":{"max":"10","min":"7"},"vis":"9","wind":{"deg":"45","dir":"�޳�������","sc":"΢��","spd":"10"}},{"astro":{"sr":"07:25","ss":"18:49"},"cond":{"code_d":"104","code_n":"104","txt_d":"��","txt_n":"��"},"date":"2016-02-24","hum":"56","pcpn":"3.0","pop":"50","pres":"1028","tmp":{"max":"11","min":"8"},"vis":"10","wind":{"deg":"47","dir":"�޳�������","sc":"΢��","spd":"7"}}]
     * hourly_forecast : [{"date":"2016-02-18 22:00","hum":"53","pop":"0","pres":"1021","tmp":"14","wind":{"deg":"13","dir":"������","sc":"΢��","spd":"16"}}]
     * now : {"cond":{"code":"101","txt":"����"},"fl":"10","hum":"57","pcpn":"0","pres":"1019","tmp":"11","vis":"10","wind":{"deg":"20","dir":"������","sc":"4-5","spd":"17"}}
     * status : ok
     * suggestion : {"comf":{"brf":"������","txt":"����������������е��е���������󲿷�����ȫ���Խ��ܡ�"},"cw":{"brf":"������","txt":"������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣"},"drsg":{"brf":"����","txt":"�����ź����׼�ë�µȷ�װ���������������Ŵ��¡������׼���ë����"},"flu":{"brf":"���׷�","txt":"��ҹ�²�ϴ󣬽��׷�����ð�����ʵ������·������ʽ�����������ע�������"},"sport":{"brf":"������","txt":"���죬�����˽��и��ֻ������˶���"},"trav":{"brf":"����","txt":"�����Ϻã��¶����ˣ�������˵���Ǻ�����Ŷ�������������������Σ������Ծ�������ܴ���Ȼ�ķ�⡣"},"uv":{"brf":"����","txt":"���������߷��������������ر�������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��"}}
     */

    @SerializedName("status")
    public String status;
    /**
     * comf : {"brf":"������","txt":"����������������е��е���������󲿷�����ȫ���Խ��ܡ�"}
     * cw : {"brf":"������","txt":"������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣"}
     * drsg : {"brf":"����","txt":"�����ź����׼�ë�µȷ�װ���������������Ŵ��¡������׼���ë����"}
     * flu : {"brf":"���׷�","txt":"��ҹ�²�ϴ󣬽��׷�����ð�����ʵ������·������ʽ�����������ע�������"}
     * sport : {"brf":"������","txt":"���죬�����˽��и��ֻ������˶���"}
     * trav : {"brf":"����","txt":"�����Ϻã��¶����ˣ�������˵���Ǻ�����Ŷ�������������������Σ������Ծ�������ܴ���Ȼ�ķ�⡣"}
     * uv : {"brf":"����","txt":"���������߷��������������ر�������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��"}
     */

    @SerializedName("suggestion")
    public SuggestionEntity suggestion;
    /**
     * astro : {"sr":"07:30","ss":"18:44"}
     * cond : {"code_d":"100","code_n":"104","txt_d":"��","txt_n":"��"}
     * date : 2016-02-18
     * hum : 38
     * pcpn : 0.0
     * pop : 0
     * pres : 1019
     * tmp : {"max":"19","min":"7"}
     * vis : 10
     * wind : {"deg":"54","dir":"�޳�������","sc":"΢��","spd":"6"}
     */

    @SerializedName("daily_forecast")
    public List<DailyForecastEntity> dailyForecast;
    /**
     * date : 2016-02-18 22:00
     * hum : 53
     * pop : 0
     * pres : 1021
     * tmp : 14
     * wind : {"deg":"13","dir":"������","sc":"΢��","spd":"16"}
     */

    @SerializedName("hourly_forecast")
    public List<HourlyForecastEntity> hourlyForecast;

    public static class AqiEntity implements Serializable {
        /**
         * aqi : 99
         * co : 1
         * no2 : 87
         * o3 : 36
         * pm10 : 106
         * pm25 : 74
         * qlty : ��
         * so2 : 16
         */

        @SerializedName("city")
        public CityEntity city;

        public static class CityEntity implements Serializable {
            @SerializedName("aqi")
            public String aqi;
            @SerializedName("co")
            public String co;
            @SerializedName("no2")
            public String no2;
            @SerializedName("o3")
            public String o3;
            @SerializedName("pm10")
            public String pm10;
            @SerializedName("pm25")
            public String pm25;
            @SerializedName("qlty")
            public String qlty;
            @SerializedName("so2")
            public String so2;
        }
    }

    public static class BasicEntity implements Serializable {
        @SerializedName("city")
        public String city;
        @SerializedName("cnty")
        public String cnty;
        @SerializedName("id")
        public String id;
        @SerializedName("lat")
        public String lat;
        @SerializedName("lon")
        public String lon;
        /**
         * loc : 2016-02-18 21:04
         * utc : 2016-02-18 13:04
         */

        @SerializedName("update")
        public UpdateEntity update;

        public static class UpdateEntity implements Serializable {
            @SerializedName("loc")
            public String loc;
            @SerializedName("utc")
            public String utc;
        }
    }

    public static class NowEntity implements Serializable {
        /**
         * code : 101
         * txt : ����
         */

        @SerializedName("cond")
        public CondEntity cond;
        @SerializedName("fl")
        public String fl;
        @SerializedName("hum")
        public String hum;
        @SerializedName("pcpn")
        public String pcpn;
        @SerializedName("pres")
        public String pres;
        @SerializedName("tmp")
        public String tmp;
        @SerializedName("vis")
        public String vis;
        /**
         * deg : 20
         * dir : ������
         * sc : 4-5
         * spd : 17
         */

        @SerializedName("wind")
        public WindEntity wind;

        public static class CondEntity implements Serializable {
            @SerializedName("code")
            public String code;
            @SerializedName("txt")
            public String txt;
        }

        public static class WindEntity implements Serializable {
            @SerializedName("deg")
            public String deg;
            @SerializedName("dir")
            public String dir;
            @SerializedName("sc")
            public String sc;
            @SerializedName("spd")
            public String spd;
        }
    }

    public static class SuggestionEntity implements Serializable {
        /**
         * brf : ������
         * txt : ����������������е��е���������󲿷�����ȫ���Խ��ܡ�
         */

        @SerializedName("comf")
        public ComfEntity comf;
        /**
         * brf : ������
         * txt : ������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣
         */

        @SerializedName("cw")
        public CwEntity cw;
        /**
         * brf : ����
         * txt : �����ź����׼�ë�µȷ�װ���������������Ŵ��¡������׼���ë����
         */

        @SerializedName("drsg")
        public DrsgEntity drsg;
        /**
         * brf : ���׷�
         * txt : ��ҹ�²�ϴ󣬽��׷�����ð�����ʵ������·������ʽ�����������ע�������
         */

        @SerializedName("flu")
        public FluEntity flu;
        /**
         * brf : ������
         * txt : ���죬�����˽��и��ֻ������˶���
         */

        @SerializedName("sport")
        public SportEntity sport;
        /**
         * brf : ����
         * txt : �����Ϻã��¶����ˣ�������˵���Ǻ�����Ŷ�������������������Σ������Ծ�������ܴ���Ȼ�ķ�⡣
         */

        @SerializedName("trav")
        public TravEntity trav;
        /**
         * brf : ����
         * txt : ���������߷��������������ر�������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��
         */

        @SerializedName("uv")
        public UvEntity uv;

        public static class ComfEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class CwEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class DrsgEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class FluEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class SportEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class TravEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }

        public static class UvEntity implements Serializable {
            @SerializedName("brf")
            public String brf;
            @SerializedName("txt")
            public String txt;
        }
    }

    public static class DailyForecastEntity implements Serializable {
        /**
         * sr : 07:30
         * ss : 18:44
         */

        @SerializedName("astro")
        public AstroEntity astro;
        /**
         * code_d : 100
         * code_n : 104
         * txt_d : ��
         * txt_n : ��
         */

        @SerializedName("cond")
        public CondEntity cond;
        @SerializedName("date")
        public String date;
        @SerializedName("hum")
        public String hum;
        @SerializedName("pcpn")
        public String pcpn;
        @SerializedName("pop")
        public String pop;
        @SerializedName("pres")
        public String pres;
        /**
         * max : 19
         * min : 7
         */

        @SerializedName("tmp")
        public TmpEntity tmp;
        @SerializedName("vis")
        public String vis;
        /**
         * deg : 54
         * dir : �޳�������
         * sc : ΢��
         * spd : 6
         */

        @SerializedName("wind")
        public WindEntity wind;

        public static class AstroEntity implements Serializable {
            @SerializedName("sr")
            public String sr;
            @SerializedName("ss")
            public String ss;
        }

        public static class CondEntity implements Serializable {
            @SerializedName("code_d")
            public String codeD;
            @SerializedName("code_n")
            public String codeN;
            @SerializedName("txt_d")
            public String txtD;
            @SerializedName("txt_n")
            public String txtN;
        }

        public static class TmpEntity implements Serializable {
            @SerializedName("max")
            public String max;
            @SerializedName("min")
            public String min;
        }

        public static class WindEntity implements Serializable {
            @SerializedName("deg")
            public String deg;
            @SerializedName("dir")
            public String dir;
            @SerializedName("sc")
            public String sc;
            @SerializedName("spd")
            public String spd;
        }
    }

    public static class HourlyForecastEntity implements Serializable {
        @SerializedName("date")
        public String date;
        @SerializedName("hum")
        public String hum;
        @SerializedName("pop")
        public String pop;
        @SerializedName("pres")
        public String pres;
        @SerializedName("tmp")
        public String tmp;
        /**
         * deg : 13
         * dir : ������
         * sc : ΢��
         * spd : 16
         */

        @SerializedName("wind")
        public WindEntity wind;

        public static class WindEntity implements Serializable {
            @SerializedName("deg")
            public String deg;
            @SerializedName("dir")
            public String dir;
            @SerializedName("sc")
            public String sc;
            @SerializedName("spd")
            public String spd;
        }
    }
}
