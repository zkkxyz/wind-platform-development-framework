package com.wind.common.utils.collection;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils extends org.springframework.util.CollectionUtils
{
  public static List<?> sortList(List<?> sortList, String propertyName, boolean isDesc)
  {
    Comparator cmp = ComparableComparator.getInstance();
    cmp = ComparatorUtils.nullLowComparator(cmp);
    if (isDesc)
      cmp = ComparatorUtils.reversedComparator(cmp);
    Comparator bcmp = new BeanComparator(propertyName, cmp);
    Collections.sort(sortList, bcmp);
    return sortList;
  }
}