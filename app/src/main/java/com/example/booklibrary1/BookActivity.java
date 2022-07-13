package com.example.booklibrary1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

       /* String longDescription = "aaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        Book book = new Book(1, " Monstress, Volume Two: The Blood (Book #2 of The Monstress Series)", "Majorie Liu (author) and Sana Takeda (artist)", 1350, "https://www.barnesandnoble.com/blog/wp-content/uploads/2019/05/monstressbookonecover-690x1024-1.jpg",
                "the book-cover of the graphic-novel Monstress – Volume Two: The Blood.", longDescription);
*/
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookId(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book incomingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for (Book b : favoriteBooks){
            if (b.getId() == book.getId()){
                existInFavoriteBooks = true;
            }
        }
        if (existInFavoriteBooks){ 
            btnAddToFavorite.setEnabled(false);
        }else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleCurrentlyReadingBooks(final Book incomingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b : currentlyReadingBooks){
            if (b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWatToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks){
            if (b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }
        if (existInWantToReadBooks){
            btnAddToWantRead.setEnabled(false);
        }else {
            btnAddToWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks){
            if (b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantRead = findViewById(R.id.btnAddToWantToReadList);

        bookImage = findViewById(R.id.imgBook);

    }
}