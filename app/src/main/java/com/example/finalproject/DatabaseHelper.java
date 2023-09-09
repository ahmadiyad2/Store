        package com.example.finalproject;

        import android.annotation.SuppressLint;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.util.Base64;
        import android.widget.ImageView;

        import androidx.annotation.Nullable;

        import java.io.ByteArrayOutputStream;
        import java.util.ArrayList;


        public class DatabaseHelper  extends SQLiteOpenHelper {

            public static final String NAME_DATABASE = "loginApp";
            static final String USER_NAME_COLUMN = "UserName";
            public static final String EMAIL_COLUMN = "Email";
            public static final String ID_COLUMN = "id";
            public static final String PASSWORD_COLUMN = "password";

            public static final String NAME_TABEL = "dataUser";


            public static final String NAME_TABEL_INFORMATION = "informtionUser";
            public static final String USER_NAME_COLUMN_INFO = "userName";
            public static final String NAME_JOP_INFO = "nameJop";
            public static final String DISCRAPTION_COLUMN_INFO = "discraption";


            public static final String TABLENAME1 = "items";
            public static final String COl_ID = "id";
            public static final String itemCOL1 = "nameProduct";
            public static final String itemCOL2 = "price";

            public static final String itemCOL3 = "photo";

            public static final String itemCOL4 = "ps";



            public static final String TABLENAME1_CART = "itemsCart";
            public static final String COl_ID_CART = "id";
            public static final String itemCOL1_CART = "nameProduct";
            public static final String itemCOL2_CART = "price";

            public static final String itemCOL3_CART = "photo";

            public static final String itemCOL4_CART = "ps";
            public static final String itemCOL5_CART = "count";


            public DatabaseHelper(@Nullable Context context) {
                super(context, NAME_DATABASE, null, 77);


            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                // الدخول
                db.execSQL(" CREATE TABLE " + NAME_TABEL + "(" + ID_COLUMN + " integer primary key  autoincrement,"
                        + USER_NAME_COLUMN + " vharchar, " + EMAIL_COLUMN + " text, " + PASSWORD_COLUMN + " text)");
                // المعلومات الشخصية
                db.execSQL("CREATE TABLE " + NAME_TABEL_INFORMATION + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + USER_NAME_COLUMN_INFO + " VARCHAR, " + NAME_JOP_INFO + " VARCHAR, " + DISCRAPTION_COLUMN_INFO + " TEXT)");

                db.execSQL("create Table " + TABLENAME1 + "(" + COl_ID + " INTEGER primary key AUTOINCREMENT, " + itemCOL1 + " TEXT," + itemCOL2 + " TEXT," +
                        itemCOL3 + " blob , "+itemCOL4+ " TEXT )");


                db.execSQL("create Table " + TABLENAME1_CART + "(" + COl_ID_CART + " INTEGER  primary key AUTOINCREMENT, " + itemCOL1_CART + " TEXT," + itemCOL2_CART + " TEXT," +
                        itemCOL3_CART + " blob , "+itemCOL4_CART+ " TEXT, "+itemCOL5_CART+" Text )");


            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                db.execSQL("Drop table if exists " + NAME_TABEL);
                db.execSQL("Drop table if exists " + NAME_TABEL_INFORMATION);
                db.execSQL("drop Table if exists " + TABLENAME1);
                db.execSQL("drop Table if exists " + TABLENAME1_CART);

                        onCreate(db);

            }


            public long insertDataUser(DataUserName dataUserName) {
                SQLiteDatabase db = getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(USER_NAME_COLUMN, dataUserName.getUserName());
                cv.put(EMAIL_COLUMN, dataUserName.getEmail());
                cv.put(PASSWORD_COLUMN, dataUserName.getPassword());

                long result = db.insert(NAME_TABEL, null, cv);

                return result;

            }


            public boolean checkLogin(String username, String password) {
                SQLiteDatabase db = this.getReadableDatabase();
                String[] columns = {ID_COLUMN};
                String selection = EMAIL_COLUMN + " = ?" + " AND " + PASSWORD_COLUMN + " = ?";
                String[] selectionArgs = {username, password};
                Cursor cursor = db.query(NAME_TABEL, columns, selection, selectionArgs, null, null, null);
                int count = cursor.getCount();
                cursor.close();
                db.close();

                return count > 0;
            }

            public long insertAdditionalUserData(String userName, String nameJop, String discraption) {
                SQLiteDatabase db = getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(USER_NAME_COLUMN_INFO, userName);
                cv.put(NAME_JOP_INFO, nameJop);
                cv.put(DISCRAPTION_COLUMN_INFO, discraption);

                long result = db.insert(NAME_TABEL_INFORMATION, null, cv);

                return result;
            }

            public String getJopNameFromUsername(String username) {
                SQLiteDatabase db = this.getReadableDatabase();
                String jopName = "";

                String[] columns = {NAME_JOP_INFO};
                String selection = USER_NAME_COLUMN_INFO + " = ?";
                String[] selectionArgs = {username};
                Cursor cursor = db.query(NAME_TABEL_INFORMATION, columns, selection, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    jopName = cursor.getString(cursor.getColumnIndexOrThrow(NAME_JOP_INFO));
                }
                cursor.close();
                db.close();

                return jopName;
            }

            public String getDescriptionFromUsername(String username) {
                SQLiteDatabase db = this.getReadableDatabase();
                String description = "";

                String[] columns = {DISCRAPTION_COLUMN_INFO};
                String selection = USER_NAME_COLUMN_INFO + " = ?";
                String[] selectionArgs = {username};
                Cursor cursor = db.query(NAME_TABEL_INFORMATION, columns, selection, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {

                    if (!cursor.isNull(cursor.getColumnIndexOrThrow(DISCRAPTION_COLUMN_INFO))) {
                        description = cursor.getString(cursor.getColumnIndexOrThrow(DISCRAPTION_COLUMN_INFO));
                    }
                }
                cursor.close();
                db.close();

                return description;
            }


            public String getUserNameFromUsername(String username) {
                SQLiteDatabase db = this.getReadableDatabase();
                String userName = "";

                String[] columns = {USER_NAME_COLUMN_INFO};
                String selection = USER_NAME_COLUMN + " = ?";
                String[] selectionArgs = {username};
                Cursor cursor = db.query(NAME_TABEL_INFORMATION, columns, selection, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    userName = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME_COLUMN_INFO));
                }
                cursor.close();
                db.close();

                return userName;
            }

            public String getUserNameFromSomeFunction() {
                SQLiteDatabase db = this.getReadableDatabase();
                String userName = "";

                String[] columns = {USER_NAME_COLUMN_INFO};
                String selection = "";
                String[] selectionArgs = null;
                    Cursor cursor = db.query(NAME_TABEL_INFORMATION, columns, selection, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    userName = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME_COLUMN_INFO));
                }
                cursor.close();
                db.close();

                return userName;
            }



            public boolean addProduct(Prodacts prodacts) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(itemCOL1, prodacts.getNameProduct());
                values.put(itemCOL2, prodacts.getPriceProduct());
                values.put(itemCOL4, prodacts.getProductPs());
                String imageString = Base64.encodeToString(prodacts.getImage(), Base64.DEFAULT);
                values.put(itemCOL3, imageString);

                long result = db.insert(TABLENAME1, null, values);
                return result != -1;
            }
            public boolean addProductCart(Prodacts prodacts) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(itemCOL1_CART, prodacts.getNameProduct());
                values.put(itemCOL2_CART, prodacts.getPriceProduct());
                values.put(itemCOL4_CART, prodacts.getProductPs());
                values.put(itemCOL5_CART, prodacts.getProductPriceCount());

                String imageString = Base64.encodeToString(prodacts.getImage(), Base64.DEFAULT);
                values.put(itemCOL3_CART, imageString);

                long result = db.insert(TABLENAME1_CART, null, values);
                return result != -1;
            }


            public ArrayList<Prodacts> getAllProducts() {
                ArrayList<Prodacts> products = new ArrayList<>();

                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME1, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COl_ID));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow(itemCOL1));
                        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(itemCOL2));

                        String imageString = cursor.getString(cursor.getColumnIndexOrThrow(itemCOL3));
                        byte[] image = Base64.decode(imageString, Base64.DEFAULT);

                        int ps = cursor.getInt(cursor.getColumnIndexOrThrow(itemCOL4));

                        Prodacts product = new Prodacts(name, price, image ,ps);
                        product.setIdProduct(id);
                        products.add(product);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                return products;
            }

            public ArrayList<Prodacts> getAllProductsCart() {
                ArrayList<Prodacts> products = new ArrayList<>();

                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME1_CART, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COl_ID));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow(itemCOL1_CART));
                        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(itemCOL2_CART));

                        String imageString = cursor.getString(cursor.getColumnIndexOrThrow(itemCOL3_CART));
                        byte[] image = Base64.decode(imageString, Base64.DEFAULT);

                        int ps = cursor.getInt(cursor.getColumnIndexOrThrow(itemCOL4_CART));

                        Prodacts product = new Prodacts(name, price, image ,ps);
                        product.setIdProduct(id);
                        products.add(product);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                return products;
            }


            public boolean deleteProductCart(int productId) {
                SQLiteDatabase db = this.getWritableDatabase();
                String selection = COl_ID_CART + " = ?";
                String[] selectionArgs = {String.valueOf(productId)};
                int rowsAffected = db.delete(TABLENAME1_CART, selection, selectionArgs);
                db.close();
                return rowsAffected != 0;
            }


            public boolean deleteProductadmin(int productId) {
                SQLiteDatabase db = this.getWritableDatabase();
                String selection = COl_ID + " = ?";
                String[] selectionArgs = {String.valueOf(productId)};
                int rowsAffected = db.delete(TABLENAME1, selection, selectionArgs);
                db.close();
                return rowsAffected != 0;
            }




            public double sumColumnValues() {
                SQLiteDatabase db = this.getReadableDatabase();
                String[] columns = {itemCOL5_CART};
                Cursor cursor = db.query(TABLENAME1_CART, columns, null, null, null, null, null);

                double sum = 0;
                if (cursor.moveToFirst()) {
                    do {
                        double value = cursor.getInt(cursor.getColumnIndexOrThrow(itemCOL5_CART));
                        sum+= value;
                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();

                return sum;
            }
             public void clearCartTable() {
                SQLiteDatabase db = getWritableDatabase();
                db.delete(TABLENAME1_CART, null, null);
                db.close();
            }

             public void clearUsersTable() {
                SQLiteDatabase db = getWritableDatabase();
                db.delete(NAME_TABEL_INFORMATION, null, null);
                db.close();
            }

            public int updateProduct(Prodacts prodacts) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(itemCOL1, prodacts.getNameProduct());
                values.put(itemCOL2, prodacts.getPriceProduct());
                values.put(itemCOL4, prodacts.getProductPs());

                String imageString = Base64.encodeToString(prodacts.getImage(), Base64.NO_WRAP);
                values.put(itemCOL3, imageString);

                String selection = COl_ID + " = ?";
                String[] selectionArgs = {String.valueOf(prodacts.getIdProduct())};
                int rowsAffected = db.update(TABLENAME1, values, selection, selectionArgs);
                db.close();
                return rowsAffected;
            }

           /* public int DeleteItem(int  id) {
                SQLiteDatabase sqLiteDatabase = getWritableDatabase();
                return sqLiteDatabase.delete(TABLENAME1_CART ,  COl_ID_CART+" = ?",  new String[]{String.valueOf(id)});

            }*/

         /*   public int updateUserInformation(String userName, String name, String description) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(USER_NAME_COLUMN_INFO, userName);
                values.put(NAME_JOP_INFO, name);
                values.put(DISCRAPTION_COLUMN_INFO, description);

                String selection = USER_NAME_COLUMN_INFO + " = ?";
                String[] selectionArgs = {userName};
                int rowsAffected = db.update(NAME_TABEL_INFORMATION, values, selection, selectionArgs);
                db.close();
                return rowsAffected;
            }*/


        }
