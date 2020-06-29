package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class TextActivity extends BaseActivity implements View.OnClickListener {
    private Bitmap mBitmap;
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        mTextView = findViewById(R.id.text_view);
        mImageView = findViewById(R.id.image_view);
        findViewById(R.id.btn_device).setOnClickListener(this);
        findViewById(R.id.image_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_device) {
            if (mBitmap != null) {
                runTextRecognition();
            }
        }
        else if(view.getId() == R.id.image_view){
            int hasWriteCameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (hasWriteCameraPermission == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 123);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_STORAGE_PERMS1:
                case RC_STORAGE_PERMS2:
                    checkStoragePermission(requestCode);
                    break;
                case RC_SELECT_PICTURE:
                    Uri dataUri = data.getData();
                    String path = MyHelper.getPath(this, dataUri);
                    if (path == null) {
                        mBitmap = MyHelper.resizeImage(imageFile, this, dataUri, mImageView);
                    } else {
                        mBitmap = MyHelper.resizeImage(imageFile, path, mImageView);
                    }
                    if (mBitmap != null) {
                        mTextView.setText(null);
                        mImageView.setImageBitmap(mBitmap);
                    }
                    break;
                case RC_TAKE_PICTURE:
                    mBitmap = MyHelper.resizeImage(imageFile, imageFile.getPath(), mImageView);
                    if (mBitmap != null) {
                        mTextView.setText(null);
                        mImageView.setImageBitmap(mBitmap);
                    }
                    break;
            }
        }
    }

    private void runTextRecognition() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(this::processTextRecognitionResult).addOnFailureListener(Throwable::printStackTrace);
    }

    private void processTextRecognitionResult(FirebaseVisionText firebaseVisionText) {
        mTextView.setText(null);
        if (firebaseVisionText.getTextBlocks().size() == 0) {
            mTextView.setText(R.string.error_not_found);
            return;
        }
        for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
            mTextView.append(block.getText());

            //In case you want to extract each line
			/*
			for (FirebaseVisionText.Line line: block.getLines()) {
				for (FirebaseVisionText.Element element: line.getElements()) {
					mTextView.append(element.getText() + " ");
				}
			}
			*/
        }
    }

    private void openCamera() {
//        imageFile = MyHelper.createTempFile(imageFile);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri photo = FileProvider.getUriForFile(this,  "hello.provider", imageFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, photo);
//        startActivityForResult(intent, RC_TAKE_PICTURE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
