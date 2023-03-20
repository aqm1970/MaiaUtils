package com.maia_business_solutions;

public class Pair<F, S>
{
  private final F first;
  private final S second;
  
  private Pair(final F first, final S second)
  {
    this.first = first;
    this.second = second;
  }
  
  /**
   * @return the first
   */
  public final F getFirst()
  {
    return first;
  }

  /**
   * @return the second
   */
  public final S getSecond()
  {
    return second;
  }

  public static <F, S> Pair<F, S> createPair(final F first, final S second)
  {
    return new Pair<F, S>(first, second);
  }
}