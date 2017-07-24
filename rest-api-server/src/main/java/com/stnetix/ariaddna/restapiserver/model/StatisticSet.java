package com.stnetix.ariaddna.restapiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contain set of statistic information about API object.
 */
@ApiModel(description = "Contain set of statistic information about API object.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

public class StatisticSet   {
  @JsonProperty("statisticSet")
  private List<Statistic> statisticSet = new ArrayList<Statistic>();

  public StatisticSet statisticSet(List<Statistic> statisticSet) {
    this.statisticSet = statisticSet;
    return this;
  }

  public StatisticSet addStatisticSetItem(Statistic statisticSetItem) {
    this.statisticSet.add(statisticSetItem);
    return this;
  }

   /**
   * Get statisticSet
   * @return statisticSet
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Statistic> getStatisticSet() {
    return statisticSet;
  }

  public void setStatisticSet(List<Statistic> statisticSet) {
    this.statisticSet = statisticSet;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatisticSet statisticSet = (StatisticSet) o;
    return Objects.equals(this.statisticSet, statisticSet.statisticSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statisticSet);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatisticSet {\n");
    
    sb.append("    statisticSet: ").append(toIndentedString(statisticSet)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

