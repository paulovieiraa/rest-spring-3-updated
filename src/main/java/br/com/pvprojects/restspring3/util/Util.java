package br.com.pvprojects.restspring3.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public final class Util {

  public static final String DATE_PATTERN = "dd-MM-yyyy";
  public static final String TIME_ZONE = "America/Sao_Paulo";

  public static String cleanCpf(String cpf) {
    cpf = cpf.replaceAll("[^0-9]+", EMPTY).trim();
    return cpf;
  }

  public static String cleanString(String string) {
    if (!StringUtils.isBlank(string)) {
      return string
          .trim()
          .replace(" ", EMPTY)
          .replace(",", EMPTY)
          .replace(".", EMPTY)
          .replace("/", EMPTY)
          .replace("_", EMPTY)
          .replace("-", EMPTY);
    }
    return string;
  }
}
