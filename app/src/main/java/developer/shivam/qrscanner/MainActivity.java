package developer.shivam.qrscanner;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class MainActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener {

    private TextView myTextView;
    private QRCodeReaderView myDecoderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDecoderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        myDecoderView.setOnQRCodeReadListener(this);

        myTextView = (TextView) findViewById(R.id.exampleTextView);
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        myTextView.setText(text);
        myDecoderView.getCameraManager().stopPreview();
        myDecoderView.setVisibility(View.INVISIBLE);
    }


    // Called when your device have no camera
    @Override
    public void cameraNotFound() {

    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {
        Log.d("QR Result", "No Code In Image");
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDecoderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myDecoderView.getCameraManager().stopPreview();
    }
}