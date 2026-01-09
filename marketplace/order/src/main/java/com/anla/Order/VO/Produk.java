package com.anla.Order.VO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produk {
    private Long id;
    private String nama;
    private String deskripsi;
    private Double harga;
    private Integer stok;
}
