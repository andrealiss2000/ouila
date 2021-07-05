package com.lpiot.ouila.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lpiot.ouila.domain.Presence;
import com.lpiot.ouila.repositories.PresenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresenceService {

    @Autowired
    PresenceRepository repository;

    public Presence addPresence(Presence presence) {
        return repository.save(presence);
    }

    public Presence getPresenceById(Long presenceId) {
        return repository.findById(presenceId).get();
    }

    public Presence updatePresence(Long id, Presence presence) {
        return repository.findById(id).map(c -> {
            c.setStatus(presence.getStatus());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(presence);
        });
    }

    public void deletePresenceById(Long presenceId) {
        repository.deleteById(presenceId);
    }

    public List<Presence> getAllPresences() {
        return repository.findAll();
    }

    /**
     * Generates a QR Code and returns the base 64 string of that QR code image.
     * 
     * @param barcodeText
     * @return
     * @throws WriterException
     * @throws IOException
     * @throws Exception
     */
    public String generateQRCodeImage(String barcodeText) throws WriterException, IOException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", bos);
        bos.close();
        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }
}
