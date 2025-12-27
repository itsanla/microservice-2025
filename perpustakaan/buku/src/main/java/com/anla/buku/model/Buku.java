package com.anla.buku.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book entity class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buku {
    /** Book identifier. */
    @JsonProperty("id")
    private Long bookId;
    /** Book title. */
    private String judul;
    /** Book author. */
    private String pengarang;
    /** Book publisher. */
    private String penerbit;
    /** Publication year. */
    @JsonProperty("tahun_terbit")
    private Integer tahunTerbit;
}