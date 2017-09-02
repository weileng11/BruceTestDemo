package com.bruce.core.database.realm;

/**
 * Author:Bruce
 * Package:com.bruce.core.database.realm
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description  realm数据库简单说明
 */

public class Explain {

//    当数据结构发生变化是，需要升级数据库。对于Realm来说，数据库升级就是迁移操作，把原来的数据库迁移到新结构的数据库。（体验：略麻烦）
//    例1：User类发生变化，移除age，新增个@Required的id字段。
//    User版本：version 0
//    String name;
//    int    age;
//    User版本：version 1
//    @Required
//    String    id;
//    String name;
//
//    链接：http://www.jianshu.com/p/37af717761cc

//可跟查询条件
//.or()                      或者
//.beginsWith()              以xxx开头
//.endsWith()                以xxx结尾
//.greaterThan()             大于
//.greaterThanOrEqualTo()    大于或等于
//.lessThan()                小于
//.lessThanOrEqualTo()       小于或等于
//.equalTo()                 等于
//.notEqualTo()              不等于
//.findAll()                 查询所有
//.average()                 平均值
//.beginGroup()              开始分组
//.endGroup()                结束分组
//.between()                 在a和b之间
//.contains()                包含xxx
//.count()                   统计数量
//.distinct()                去除重复
//.findFirst()               返回结果集的第一行记录
//.isNotEmpty()              非空串
//.isEmpty()                 为空串
//.isNotNull()               非空对象
//.isNull()                  为空对象
//.max()                     最大值
//.maximumDate()             最大日期
//.min()                     最小值
//.minimumDate()             最小日期
//.sum()                     求和


//    /**
//     * Author:Bruce
//     * time:2017/8/24.
//     * contact：weileng143@163.com*
//     */
//    public class MainActivity extends AppCompatActivity {
//        private BaseRealmDao userDao;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            userDao = new BaseRealmDao(this);
//            try {
//                userDao.deleteAll(Explian.class);//先删除所有，以免demo出现主键已经存在的异常
////            userDao.deleteAll(User.class);//先删除所有，以免demo出现主键已经存在的异常
//                User user = new User();
//                user.setId(10);
//                user.setName("小刺猬");
//                user.setAge(22);
//                user.setHasGrilFriend(true);
//                Explian test2 = new Explian();
//                test2.setMid(11);
//                test2.setName("一座古城");
//                RealmList<ElseExplain> tests=new RealmList<>();
//                tests.add(test2);
//                user.setTest(tests);
//                userDao.insert(user);
//
//                Explian Explain = new Explian();
//                Explain.setMid(14);
//                Explain.setName("一座古城");
//                userDao.insert(Explain);
//
//
//                Log.d("flag", "插入小刺猬----" + userDao.getAllUser(new User()).toString());
//                Log.d("flag", "一座古城----" + userDao.getAllUser(new ElseExplain()).toString());
//
//                for (int i = 0; i < 5; i++) {
//                    userDao.insert(new User(i,"一座深林"+i,20+i));
//                }
//
//                for (int i = 0; i < 5; i++) {
//                    userDao.insert(new ElseExplain(i,"一座古城"+i));
//                }
//                Log.d("flag","插入5个对象----"+userDao.getAllUser(new ElseExplain()).toString());
//                Log.d("flag","插入5个对象----"+userDao.getAllUser(new User()).toString());
//                Log.d("flag","查询1----"+userDao.findByNameOrAge(new User(),"一座深林1",20));
//                Log.d("flag","查询2----"+userDao.findByNameOrAge(new User(),"一座深林2",23));
////            User u2=new User();
////            u2.setId(1);
////            u2.setName("yizuoshenlin");
//                User u= (User) userDao.updateUser(new User(),"一座深林3");
//                u.setName("yizuoshenlinxx");
//                userDao.commitToTransaction();
//                Log.d("flag","更新1----"+userDao.findByNameOrAge(new User(),"yizuoshenlinxx",23));
//                userDao.deleteUser(new User(),0);//删除0
////            Log.d("flag","删除后查看----"+userDao.getAllUser().toString());
//                Log.d("flag","平均年龄----"+userDao.selectMaxAverage(new User()));
//                List<User> allUser = userDao.getAllUser(new ElseExplain());
//                userDao.deleteData(new User(),3);
//
//                Log.d("flag","查询所有的数据----"+ allUser.size());
//                Log.d("flag","查询指定删除后的数据----"+userDao.getAllUser(new User()).toString());
//                Log.d("flag","查询所有删除的数据----"+ userDao.getAllUser(new User()).size());
//                //统一关闭事务
//                userDao.closeRealm();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
////    最后在销毁Activity或Fragment时，要取消掉异步任务
//
//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
////        if (addTask!=null&&!addTask.isCancelled()){
////            addTask.cancel();
////        }
//        }

}
