package com.calvin.skripsi.kanabisa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.calvin.skripsi.kanabisa.model.*

class DBHelper (context: Context) : SQLiteOpenHelper(context, "DB_KANABISA", null, 1) {
    val arrKarakter: ArrayList<Karakter> = ArrayList<Karakter>(208)
    val arrJenis: ArrayList<Jenis> = ArrayList<Jenis>(2)
    val arrBunyi: ArrayList<Bunyi> = ArrayList<Bunyi>(2)
    val arrEvaluasi: ArrayList<Evaluasi> = ArrayList<Evaluasi>()

    override fun onCreate(db: SQLiteDatabase?) {
        val createKarakter = "CREATE TABLE IF NOT EXISTS TBL_KARAKTER(" +
                "ID INTEGER PRIMARY KEY," +
                "KARAKTER TEXT," +
                "ROMAN TEXT," +
                "ID_JENIS INTEGER," +
                "ID_BUNYI INTEGER," +
                "GAMBAR TEXT," +
                "EVAL_BANYAK INTEGER," +
                "EVAL_BENAR INTEGER," +
                "EVAL_NILAI REAL)"
        val createJenis = "CREATE TABLE IF NOT EXISTS TBL_JENIS(" +
                "ID INTEGER PRIMARY KEY," +
                "JENIS TEXT)"
        val createBunyi = "CREATE TABLE IF NOT EXISTS TBL_BUNYI(" +
                "ID INTEGER PRIMARY KEY," +
                "BUNYI TEXT)"
        val createEvaluasi = "CREATE TABLE IF NOT EXISTS TBL_EVALUASI(" +
                "ID INTEGER PRIMARY KEY," +
                "ID_KARAKTER INTEGER," +
                "TANGGAL TEXT," +
                "STATUS INTEGER)"
        db!!.execSQL(createKarakter)
        db!!.execSQL(createJenis)
        db!!.execSQL(createBunyi)
        db!!.execSQL(createEvaluasi)

        initArrKarakter()
        initArrJenis()
        initArrBunyi()

        initArrKarakterSQL(db)
        initArrJenisSQL(db)
        initArrBunyiSQL(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun initArrKarakter() {
        // HIRAGANA

        // SEION

        // a-i-u-e-o
        arrKarakter.add(Karakter(1,"あ","a",1,1,"a",0,0,0))
        arrKarakter.add(Karakter(2,"い","i",1,1,"i",0,0,0))
        arrKarakter.add(Karakter(3,"う","u",1,1,"u",0,0,0))
        arrKarakter.add(Karakter(4,"え","e",1,1,"e",0,0,0))
        arrKarakter.add(Karakter(5,"お","o",1,1,"o",0,0,0))

        // ka-ki-ku-ke-ko
        arrKarakter.add(Karakter(6,"か","ka",1,1,"ka",0,0,0))
        arrKarakter.add(Karakter(7,"き","ki",1,1,"ki",0,0,0))
        arrKarakter.add(Karakter(8,"く","ku",1,1,"ku",0,0,0))
        arrKarakter.add(Karakter(9,"け","ke",1,1,"ke",0,0,0))
        arrKarakter.add(Karakter(10,"こ","ko",1,1,"ko",0,0,0))

        // sa-shi-su-se-so
        arrKarakter.add(Karakter(11,"さ","sa",1,1,"sa",0,0,0))
        arrKarakter.add(Karakter(12,"し","shi",1,1,"shi",0,0,0))
        arrKarakter.add(Karakter(13,"す","su",1,1,"su",0,0,0))
        arrKarakter.add(Karakter(14,"せ","se",1,1,"se",0,0,0))
        arrKarakter.add(Karakter(15,"そ","so",1,1,"so",0,0,0))

        // ta-chi-tsu-te-to
        arrKarakter.add(Karakter(16,"た","ta",1,1,"ta",0,0,0))
        arrKarakter.add(Karakter(17,"ち","chi",1,1,"chi",0,0,0))
        arrKarakter.add(Karakter(18,"つ","tsu",1,1,"tsu",0,0,0))
        arrKarakter.add(Karakter(19,"て","te",1,1,"te",0,0,0))
        arrKarakter.add(Karakter(20,"と","to",1,1,"to",0,0,0))

        // na-ni-nu-ne-no
        arrKarakter.add(Karakter(21,"な","na",1,1,"na",0,0,0))
        arrKarakter.add(Karakter(22,"に","ni",1,1,"ni",0,0,0))
        arrKarakter.add(Karakter(23,"に","nu",1,1,"nu",0,0,0))
        arrKarakter.add(Karakter(24,"ね","ne",1,1,"ne",0,0,0))
        arrKarakter.add(Karakter(25,"の","no",1,1,"no",0,0,0))

        // ha-hi-fu-he-ho
        arrKarakter.add(Karakter(26,"は","ha",1,1,"ha",0,0,0))
        arrKarakter.add(Karakter(27,"ひ","hi",1,1,"hi",0,0,0))
        arrKarakter.add(Karakter(28,"ふ","fu",1,1,"fu",0,0,0))
        arrKarakter.add(Karakter(29,"へ","he",1,1,"he",0,0,0))
        arrKarakter.add(Karakter(30,"ほ","ho",1,1,"ho",0,0,0))

        // ma-mi-mu-me-mo
        arrKarakter.add(Karakter(31,"ま","ma",1,1,"ma",0,0,0))
        arrKarakter.add(Karakter(32,"み","mi",1,1,"mi",0,0,0))
        arrKarakter.add(Karakter(33,"む","mu",1,1,"mu",0,0,0))
        arrKarakter.add(Karakter(34,"め","me",1,1,"me",0,0,0))
        arrKarakter.add(Karakter(35,"も","mo",1,1,"mo",0,0,0))

        // ya-yu-yo
        arrKarakter.add(Karakter(36,"や","ya",1,1,"ya",0,0,0))
        arrKarakter.add(Karakter(37,"ゆ","yu",1,1,"yu",0,0,0))
        arrKarakter.add(Karakter(38,"よ","yo",1,1,"yo",0,0,0))

        // ra-ri-ru-re-ro
        arrKarakter.add(Karakter(39,"ら","ra",1,1,"ra",0,0,0))
        arrKarakter.add(Karakter(40,"り","ri",1,1,"ri",0,0,0))
        arrKarakter.add(Karakter(41,"る","ru",1,1,"ru",0,0,0))
        arrKarakter.add(Karakter(42,"れ","re",1,1,"re",0,0,0))
        arrKarakter.add(Karakter(43,"ろ","ro",1,1,"ro",0,0,0))

        // wa-o (wo)-n
        arrKarakter.add(Karakter(44,"わ","wa",1,1,"wa",0,0,0))
        arrKarakter.add(Karakter(45,"を","o (wo)",1,1,"wo",0,0,0))
        arrKarakter.add(Karakter(46,"ん","n",1,1,"n",0,0,0))

        // ga-gi-gu-ge-go
        arrKarakter.add(Karakter(47,"が","ga",1,1,"ga",0,0,0))
        arrKarakter.add(Karakter(48,"ぎ","gi",1,1,"gi",0,0,0))
        arrKarakter.add(Karakter(49,"ぐ","gu",1,1,"gu",0,0,0))
        arrKarakter.add(Karakter(50,"げ","ge",1,1,"ge",0,0,0))
        arrKarakter.add(Karakter(51,"ご","go",1,1,"go",0,0,0))

        // za-ji (zi)-zu-ze-zo
        arrKarakter.add(Karakter(52,"ざ","za",1,1,"za",0,0,0))
        arrKarakter.add(Karakter(53,"じ","ji (zi)",1,1,"ji",0,0,0))
        arrKarakter.add(Karakter(54,"ず","zu",1,1,"zu",0,0,0))
        arrKarakter.add(Karakter(55,"ぜ","ze",1,1,"ze",0,0,0))
        arrKarakter.add(Karakter(56,"ぞ","zo",1,1,"zo",0,0,0))

        // da-ji (di)-zu (du)-de-do
        arrKarakter.add(Karakter(57,"だ","da",1,1,"da",0,0,0))
        arrKarakter.add(Karakter(58,"ぢ","ji (di)",1,1,"di",0,0,0))
        arrKarakter.add(Karakter(59,"づ","zu (du)",1,1,"du",0,0,0))
        arrKarakter.add(Karakter(60,"で","de",1,1,"de",0,0,0))
        arrKarakter.add(Karakter(61,"ど","do",1,1,"do",0,0,0))

        // ba-bi-bu-be-bo
        arrKarakter.add(Karakter(62,"ば","ba",1,1,"ba",0,0,0))
        arrKarakter.add(Karakter(63,"び","bi",1,1,"bi",0,0,0))
        arrKarakter.add(Karakter(64,"ぶ","bu",1,1,"bu",0,0,0))
        arrKarakter.add(Karakter(65,"べ","be",1,1,"be",0,0,0))
        arrKarakter.add(Karakter(66,"ぼ","bo",1,1,"bo",0,0,0))

        // pa-pi-pu-pe-po
        arrKarakter.add(Karakter(67,"ぱ","pa",1,1,"pa",0,0,0))
        arrKarakter.add(Karakter(68,"ぴ","pi",1,1,"pi",0,0,0))
        arrKarakter.add(Karakter(69,"ぷ","pu",1,1,"pu",0,0,0))
        arrKarakter.add(Karakter(70,"ぺ","pe",1,1,"pe",0,0,0))
        arrKarakter.add(Karakter(71,"ぽ","po",1,1,"po",0,0,0))

        // YOON

        // kya-kyu-kyo
        arrKarakter.add(Karakter(72,"きゃ","kya",1,2,"kya",0,0,0))
        arrKarakter.add(Karakter(73,"きゅ","kyu",1,2,"kyu",0,0,0))
        arrKarakter.add(Karakter(74,"きょ","kyo",1,2,"kyo",0,0,0))

        // sha-shu-sho
        arrKarakter.add(Karakter(75,"しゃ","sha",1,2,"sha",0,0,0))
        arrKarakter.add(Karakter(76,"しゅ","shu",1,2,"shu",0,0,0))
        arrKarakter.add(Karakter(77,"しょ","sho",1,2,"sho",0,0,0))

        // cha-chu-cho
        arrKarakter.add(Karakter(78,"ちゃ","cha",1,2,"cha",0,0,0))
        arrKarakter.add(Karakter(79,"ちゅ","chu",1,2,"chu",0,0,0))
        arrKarakter.add(Karakter(80,"ちょ","cho",1,2,"cho",0,0,0))

        // nya-nyu-nyo
        arrKarakter.add(Karakter(81,"にゃ","nya",1,2,"nya",0,0,0))
        arrKarakter.add(Karakter(82,"にゅ","nyu",1,2,"nyu",0,0,0))
        arrKarakter.add(Karakter(83,"にょ","nyo",1,2,"nyo",0,0,0))

        // hya-hyu-hyo
        arrKarakter.add(Karakter(84,"ひゃ","hya",1,2,"hya",0,0,0))
        arrKarakter.add(Karakter(85,"ひゅ","hyu",1,2,"hyu",0,0,0))
        arrKarakter.add(Karakter(86,"ひょ","hyo",1,2,"hyo",0,0,0))

        // mya-myu-myo
        arrKarakter.add(Karakter(87,"みゃ","mya",1,2,"mya",0,0,0))
        arrKarakter.add(Karakter(88,"みゅ","myu",1,2,"myu",0,0,0))
        arrKarakter.add(Karakter(89,"みょ","myo",1,2,"myo",0,0,0))

        // rya-ryu-ryo
        arrKarakter.add(Karakter(90,"りゃ","rya",1,2,"rya",0,0,0))
        arrKarakter.add(Karakter(91,"りゅ","ryu",1,2,"ryu",0,0,0))
        arrKarakter.add(Karakter(92,"りょ","ryo",1,2,"ryo",0,0,0))

        // gya-gyu-gyo
        arrKarakter.add(Karakter(93,"ぎゃ","gya",1,2,"gya",0,0,0))
        arrKarakter.add(Karakter(94,"ぎゅ","gyu",1,2,"gyu",0,0,0))
        arrKarakter.add(Karakter(95,"ぎょ","gyo",1,2,"gyo",0,0,0))

        // ja-ju-jo
        arrKarakter.add(Karakter(96,"じゃ","ja",1,2,"ja",0,0,0))
        arrKarakter.add(Karakter(97,"じゅ","ju",1,2,"ju",0,0,0))
        arrKarakter.add(Karakter(98,"じょ","jo",1,2,"jo",0,0,0))

        // bya-byu-byo
        arrKarakter.add(Karakter(99,"びゃ","bya",1,2,"bya",0,0,0))
        arrKarakter.add(Karakter(100,"びゅ","byu",1,2,"byu",0,0,0))
        arrKarakter.add(Karakter(101,"びょ","byo",1,2,"byo",0,0,0))

        // pya-pyu-pyo
        arrKarakter.add(Karakter(102,"ぴゃ","pya",1,2,"pya",0,0,0))
        arrKarakter.add(Karakter(103,"ぴゅ","pyu",1,2,"pyu",0,0,0))
        arrKarakter.add(Karakter(104,"ぴょ","pyo",1,2,"pyo",0,0,0))

        // KATAKANA

        // SEION

        // a-i-u-e-o
        arrKarakter.add(Karakter(105,"ア","a",2,1,"a",0,0,0))
        arrKarakter.add(Karakter(106,"イ","i",2,1,"i",0,0,0))
        arrKarakter.add(Karakter(107,"ウ","u",2,1,"u",0,0,0))
        arrKarakter.add(Karakter(108,"エ","e",2,1,"e",0,0,0))
        arrKarakter.add(Karakter(109,"オ","o",2,1,"o",0,0,0))

        // ka-ki-ku-ke-ko
        arrKarakter.add(Karakter(110,"カ","ka",2,1,"ka",0,0,0))
        arrKarakter.add(Karakter(111,"キ","ki",2,1,"ki",0,0,0))
        arrKarakter.add(Karakter(112,"ク","ku",2,1,"ku",0,0,0))
        arrKarakter.add(Karakter(113,"ケ","ke",2,1,"ke",0,0,0))
        arrKarakter.add(Karakter(114,"コ","ko",2,1,"ko",0,0,0))

        // sa-shi-su-se-so
        arrKarakter.add(Karakter(115,"サ","sa",2,1,"sa",0,0,0))
        arrKarakter.add(Karakter(116,"シ","shi",2,1,"shi",0,0,0))
        arrKarakter.add(Karakter(117,"ス","su",2,1,"su",0,0,0))
        arrKarakter.add(Karakter(118,"セ","se",2,1,"se",0,0,0))
        arrKarakter.add(Karakter(119,"ソ","so",2,1,"so",0,0,0))

        // ta-chi-tsu-te-to
        arrKarakter.add(Karakter(120,"タ","ta",2,1,"ta",0,0,0))
        arrKarakter.add(Karakter(121,"チ","chi",2,1,"chi",0,0,0))
        arrKarakter.add(Karakter(122,"ツ","tsu",2,1,"tsu",0,0,0))
        arrKarakter.add(Karakter(123,"テ","te",2,1,"te",0,0,0))
        arrKarakter.add(Karakter(124,"ト","to",2,1,"to",0,0,0))

        // na-ni-nu-ne-no
        arrKarakter.add(Karakter(125,"ナ","na",2,1,"na",0,0,0))
        arrKarakter.add(Karakter(126,"ニ","ni",2,1,"ni",0,0,0))
        arrKarakter.add(Karakter(127,"ヌ","nu",2,1,"nu",0,0,0))
        arrKarakter.add(Karakter(128,"ネ","ne",2,1,"ne",0,0,0))
        arrKarakter.add(Karakter(129,"ノ","no",2,1,"no",0,0,0))

        // ha-hi-fu-he-ho
        arrKarakter.add(Karakter(130,"ハ","ha",2,1,"ha",0,0,0))
        arrKarakter.add(Karakter(131,"ヒ","hi",2,1,"hi",0,0,0))
        arrKarakter.add(Karakter(132,"フ","fu",2,1,"fu",0,0,0))
        arrKarakter.add(Karakter(133,"ヘ","he",2,1,"he",0,0,0))
        arrKarakter.add(Karakter(134,"ホ","ho",2,1,"ho",0,0,0))

        // ma-mi-mu-me-mo
        arrKarakter.add(Karakter(135,"マ","ma",2,1,"ma",0,0,0))
        arrKarakter.add(Karakter(136,"ミ","mi",2,1,"mi",0,0,0))
        arrKarakter.add(Karakter(137,"ム","mu",2,1,"mu",0,0,0))
        arrKarakter.add(Karakter(138,"メ","me",2,1,"me",0,0,0))
        arrKarakter.add(Karakter(139,"モ","mo",2,1,"mo",0,0,0))

        // ya-yu-yo
        arrKarakter.add(Karakter(140,"ヤ","ya",2,1,"ya",0,0,0))
        arrKarakter.add(Karakter(141,"ユ","yu",2,1,"yu",0,0,0))
        arrKarakter.add(Karakter(142,"ヨ","yo",2,1,"yo",0,0,0))

        // ra-ri-ru-re-ro
        arrKarakter.add(Karakter(143,"ラ","ra",2,1,"ra",0,0,0))
        arrKarakter.add(Karakter(144,"リ","ri",2,1,"ri",0,0,0))
        arrKarakter.add(Karakter(145,"ル","ru",2,1,"ru",0,0,0))
        arrKarakter.add(Karakter(146,"レ","re",2,1,"re",0,0,0))
        arrKarakter.add(Karakter(147,"ロ","ro",2,1,"ro",0,0,0))

        // wa-o (wo)-n
        arrKarakter.add(Karakter(148,"ワ","wa",2,1,"wa",0,0,0))
        arrKarakter.add(Karakter(149,"ヲ","o (wo)",2,1,"wo",0,0,0))
        arrKarakter.add(Karakter(150,"ン","n",2,1,"n",0,0,0))

        // ga-gi-gu-ge-go
        arrKarakter.add(Karakter(151,"ガ","ga",2,1,"ga",0,0,0))
        arrKarakter.add(Karakter(152,"ギ","gi",2,1,"gi",0,0,0))
        arrKarakter.add(Karakter(153,"グ","gu",2,1,"gu",0,0,0))
        arrKarakter.add(Karakter(154,"ゲ","ge",2,1,"ge",0,0,0))
        arrKarakter.add(Karakter(155,"ゴ","go",2,1,"go",0,0,0))

        // za-ji (zi)-zu-ze-zo
        arrKarakter.add(Karakter(156,"ザ","za",2,1,"za",0,0,0))
        arrKarakter.add(Karakter(157,"ジ","ji (zi)",2,1,"ji",0,0,0))
        arrKarakter.add(Karakter(158,"ズ","zu",2,1,"zu",0,0,0))
        arrKarakter.add(Karakter(159,"ゼ","ze",2,1,"ze",0,0,0))
        arrKarakter.add(Karakter(160,"ゾ","zo",2,1,"zo",0,0,0))

        // da-ji (di)-zu (du)-de-do
        arrKarakter.add(Karakter(161,"ダ","da",2,1,"da",0,0,0))
        arrKarakter.add(Karakter(162,"ヂ","ji (di)",2,1,"di",0,0,0))
        arrKarakter.add(Karakter(163,"ヅ","zu (du)",2,1,"du",0,0,0))
        arrKarakter.add(Karakter(164,"デ","de",2,1,"de",0,0,0))
        arrKarakter.add(Karakter(165,"ド","do",2,1,"do",0,0,0))

        // ba-bi-bu-be-bo
        arrKarakter.add(Karakter(166,"バ","ba",2,1,"ba",0,0,0))
        arrKarakter.add(Karakter(167,"ビ","bi",2,1,"bi",0,0,0))
        arrKarakter.add(Karakter(168,"ブ","bu",2,1,"bu",0,0,0))
        arrKarakter.add(Karakter(169,"ベ","be",2,1,"be",0,0,0))
        arrKarakter.add(Karakter(170,"ボ","bo",2,1,"bo",0,0,0))

        // pa-pi-pu-pe-po
        arrKarakter.add(Karakter(171,"パ","pa",2,1,"pa",0,0,0))
        arrKarakter.add(Karakter(172,"ピ","pi",2,1,"pi",0,0,0))
        arrKarakter.add(Karakter(173,"プ","pu",2,1,"pu",0,0,0))
        arrKarakter.add(Karakter(174,"ペ","pe",2,1,"pe",0,0,0))
        arrKarakter.add(Karakter(175,"ポ","po",2,1,"po",0,0,0))

        // YOON

        // kya-kyu-kyo
        arrKarakter.add(Karakter(176,"キャ","kya",2,2,"kya",0,0,0))
        arrKarakter.add(Karakter(177,"キュ","kyu",2,2,"kyu",0,0,0))
        arrKarakter.add(Karakter(178,"キョ","kyo",2,2,"kyo",0,0,0))

        // sha-shu-sho
        arrKarakter.add(Karakter(179,"シャ","sha",2,2,"sha",0,0,0))
        arrKarakter.add(Karakter(180,"シュ","shu",2,2,"shu",0,0,0))
        arrKarakter.add(Karakter(181,"ショ","sho",2,2,"sho",0,0,0))

        // cha-chu-cho
        arrKarakter.add(Karakter(182,"チャ","cha",2,2,"cha",0,0,0))
        arrKarakter.add(Karakter(183,"チュ","chu",2,2,"chu",0,0,0))
        arrKarakter.add(Karakter(184,"チョ","cho",2,2,"cho",0,0,0))

        // nya-nyu-nyo
        arrKarakter.add(Karakter(185,"ニャ","nya",2,2,"nya",0,0,0))
        arrKarakter.add(Karakter(186,"ニュ","nyu",2,2,"nyu",0,0,0))
        arrKarakter.add(Karakter(187,"ニョ","nyo",2,2,"nyo",0,0,0))

        // hya-hyu-hyo
        arrKarakter.add(Karakter(188,"ヒャ","hya",2,2,"hya",0,0,0))
        arrKarakter.add(Karakter(189,"ヒュ","hyu",2,2,"hyu",0,0,0))
        arrKarakter.add(Karakter(190,"ヒョ","hyo",2,2,"hyo",0,0,0))

        // mya-myu-myo
        arrKarakter.add(Karakter(191,"ミャ","mya",2,2,"mya",0,0,0))
        arrKarakter.add(Karakter(192,"ミュ","myu",2,2,"myu",0,0,0))
        arrKarakter.add(Karakter(193,"ミョ","myo",2,2,"myo",0,0,0))

        // rya-ryu-ryo
        arrKarakter.add(Karakter(194,"リャ","rya",2,2,"rya",0,0,0))
        arrKarakter.add(Karakter(195,"リュ","ryu",2,2,"ryu",0,0,0))
        arrKarakter.add(Karakter(196,"リョ","ryo",2,2,"ryo",0,0,0))

        // gya-gyu-gyo
        arrKarakter.add(Karakter(197,"ギャ","gya",2,2,"gya",0,0,0))
        arrKarakter.add(Karakter(198,"ギュ","gyu",2,2,"gyu",0,0,0))
        arrKarakter.add(Karakter(199,"ギョ","gyo",2,2,"gyo",0,0,0))

        // ja-ju-jo
        arrKarakter.add(Karakter(200,"ジャ","ja",2,2,"ja",0,0,0))
        arrKarakter.add(Karakter(201,"ジュ","ju",2,2,"ju",0,0,0))
        arrKarakter.add(Karakter(202,"ジョ","jo",2,2,"jo",0,0,0))

        // bya-byu-byo
        arrKarakter.add(Karakter(203,"ビャ","bya",2,2,"bya",0,0,0))
        arrKarakter.add(Karakter(204,"ビュ","byu",2,2,"byu",0,0,0))
        arrKarakter.add(Karakter(205,"ビョ","byo",2,2,"byo",0,0,0))

        // pya-pyu-pyo
        arrKarakter.add(Karakter(206,"ピャ","pya",2,2,"pya",0,0,0))
        arrKarakter.add(Karakter(207,"ピュ","pyu",2,2,"pyu",0,0,0))
        arrKarakter.add(Karakter(208,"ピョ","pyo",2,2,"pyo",0,0,0))
    }

    fun initArrJenis() {
        arrJenis.add(Jenis(1,"Hiragana"))
        arrJenis.add(Jenis(2,"Katakana"))
    }

    fun initArrBunyi() {
        arrBunyi.add(Bunyi(1,"Seion"))
        arrBunyi.add(Bunyi(2,"Yoon"))
    }

    fun initArrKarakterSQL(db: SQLiteDatabase) {
        val cv = ContentValues()
        for (i in arrKarakter.indices) {
            var kr: Karakter = arrKarakter[i]
            cv.put("KARAKTER",kr.karakter)
            cv.put("ROMAN",kr.roman)
            cv.put("ID_JENIS",kr.id_jenis)
            cv.put("ID_BUNYI",kr.id_bunyi)
            cv.put("GAMBAR",kr.gambar)
            cv.put("EVAL_BANYAK",kr.eval_banyak)
            cv.put("EVAL_BENAR",kr.eval_benar)
            cv.put("EVAL_NILAI",kr.eval_nilai)
            db!!.insert("TBL_KARAKTER",null,cv)
        }
    }

    fun initArrJenisSQL(db: SQLiteDatabase) {
        val cv = ContentValues()
        for (i in arrJenis.indices) {
            var jn: Jenis = arrJenis[i]
            cv.put("JENIS",jn.jenis)
            db!!.insert("TBL_JENIS",null,cv)
        }
    }

    fun initArrBunyiSQL(db: SQLiteDatabase) {
        val cv = ContentValues()
        for (i in arrBunyi.indices) {
            val bn: Bunyi = arrBunyi[i]
            cv.put("BUNYI",bn.bunyi)
            db!!.insert("TBL_BUNYI",null,cv)
        }
    }

    fun cariKarakter(id_kar: Int): Karakter {
        var kr: Karakter = Karakter(0,"-","-",0,0,"-",0,0,0)
        val db = this.readableDatabase
        val rs: Cursor = db.rawQuery("SELECT * FROM TBL_KARAKTER WHERE ID = "+id_kar+";",null)
        if (rs != null) {
            if (rs.moveToFirst()) {
                do {
                    if (rs.getInt(rs.getColumnIndex("ID")).equals(id_kar)) {
                        kr = Karakter(
                            rs.getInt(rs.getColumnIndex("ID")),
                            rs.getString(rs.getColumnIndex("KARAKTER")),
                            rs.getString(rs.getColumnIndex("ROMAN")),
                            rs.getInt(rs.getColumnIndex("ID_JENIS")),
                            rs.getInt(rs.getColumnIndex("ID_BUNYI")),
                            rs.getString(rs.getColumnIndex("GAMBAR")),
                            rs.getInt(rs.getColumnIndex("EVAL_BANYAK")),
                            rs.getInt(rs.getColumnIndex("EVAL_BENAR")),
                            rs.getLong(rs.getColumnIndex("EVAL_NILAI"))
                            )
                    }
                } while (rs.moveToNext())
            }
        }
        return kr
    }

    fun tabelKarakter(): ArrayList<Karakter> {
        var arrKr: ArrayList<Karakter> = ArrayList<Karakter>(208)
        val db = this.readableDatabase
        val rs: Cursor = db.rawQuery("SELECT * FROM TBL_KARAKTER",null)
        if (rs != null) {
            if (rs.moveToFirst()) {
                do {
                    arrKr.add(Karakter(
                        rs.getInt(rs.getColumnIndex("ID")),
                        rs.getString(rs.getColumnIndex("KARAKTER")),
                        rs.getString(rs.getColumnIndex("ROMAN")),
                        rs.getInt(rs.getColumnIndex("ID_JENIS")),
                        rs.getInt(rs.getColumnIndex("ID_BUNYI")),
                        rs.getString(rs.getColumnIndex("GAMBAR")),
                        rs.getInt(rs.getColumnIndex("EVAL_BANYAK")),
                        rs.getInt(rs.getColumnIndex("EVAL_BENAR")),
                        rs.getLong(rs.getColumnIndex("EVAL_NILAI"))
                        ))
                } while (rs.moveToNext())
            }
        }
        return arrKr
    }
}