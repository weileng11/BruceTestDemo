package com.bruce.core.database.greendao;

/**
 * Author:Bruce
 * Package:com.bruce.core.database.greendao
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description   greendao操作类列子
 * 1.将BaseApplication 数据库初始化
 * 2.按照下面的操作
 * 3.原文链接:https://github.com/liangjingdev/GreenDao3Test
 */

public class Explain {
//    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//        private OptionRecyclerView mRv;
//        private AdapterForRecyclerView mAdapter;
//        private List<Student> mList; //要显示到列表的数据
//        private List<Student> dbList;  //存进数据库的数据
//        private StudentHelper mHelper;
//        private Button addButton; //添加数据
//        private Button deleteButton; //删除数据
//        private Student mStudent;
//        private boolean isSame = false; //判断是否有相同的数据
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            init();
//        }
//
//        private void init() {
//
//            mStudent = new Student();
//            addButton = (Button) findViewById(R.id.bt_add);
//            deleteButton = (Button) findViewById(R.id.bt_minus);
//            addButton.setOnClickListener(this);
//            deleteButton.setOnClickListener(this);
//
//            mHelper = DbUtil.getStudentHelper();
//            mList = new ArrayList<>();
//            dbList = new ArrayList<>();
//            initData();
//        }
//
//        /**
//         * function:显示数据到列表
//         */
//        private void showData() {
//            mRv = (OptionRecyclerView) findViewById(R.id.rv);
//            mAdapter = new AdapterForRecyclerView<Student>(this, mList, R.layout.student_item_layout) {
//                @Override
//                public void convert(ViewHolderForRecyclerView holder, Student student, int position) {
//                    holder.setText(R.id.tv_id, String.valueOf(student.getId()));
//                    holder.setText(R.id.tv_age, student.getAge());
//                    holder.setText(R.id.tv_name, student.getName());
//                    holder.setText(R.id.tv_number, student.getNumber());
//                }
//            };
//            mRv.setAdapter(mAdapter);
//        }
//
//
//        /**
//         * function:添加基本数据进入数据库
//         */
//        private void initData() {
//
//            for (int i = 0; i < 10; i++) {
//                Student student = new Student();
//                student.setId((long) i);
//                student.setAge("19" + i);
//                student.setName("小明" + 1);
//                student.setScore("99" + i);
//                student.setNumber("132321" + i);
//                dbList.add(student);
//            }
//            mHelper.save(dbList);
//            mList = dbList;
//            //显示数据
//            showData();
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//
//                //添加单个数据
//                case R.id.bt_add:
//                    mStudent.setId((long) 50);
//                    mStudent.setAge("22");
//                    mStudent.setName("小红");
//                    mStudent.setScore("100");
//                    mStudent.setNumber("2773");
//                    mHelper.saveOrUpdate(mStudent);
//
//                    //给列表添加数据
//                    addItemToList();
//
//                    break;
//
//                //移除单个数据
//                case R.id.bt_minus:
//                    mStudent = mHelper.query((long) 0);
//                    mHelper.deleteByKey((long) 0);
//
//                    //让列表移除数据
//                    removeItemFromList();
//                    break;
//            }
//        }
//
//
//        /**
//         * function:从列表中删除数据
//         */
//        private void removeItemFromList() {
//            mAdapter.removeItem(mStudent);
//        }
//
//        /**
//         * 添加数据到列表
//         */
//        private void addItemToList() {
//
//            //判断所添加的数据在列表中是否已经存在
//            for (int i = 0; i < mList.size(); i++) {
//                if (mStudent.getId() == mList.get(i).getId()) {
//                    isSame = true;
//
//                }
//            }
//
//            if (!isSame) {
//                mAdapter.addLastItem(mStudent);
//            } else {
//                Toast.makeText(this, "已有相同数据", Toast.LENGTH_SHORT).show();
//                isSame = false;
//            }
//        }
}
