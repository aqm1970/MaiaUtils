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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Pair [first=").append(first).append(", second=")
        .append(second).append("]");
    return builder.toString();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((first == null) ? 0 : first.hashCode());
    result = prime * result + ((second == null) ? 0 : second.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Pair)) {
      return false;
    }
    Pair<?, ?> other = (Pair<?, ?>) obj;
    
    if (first == null) {
      if (other.first != null) {
        return false;
      }
    }
    else if (!first.equals(other.first)) {
      return false;
    }
    if (second == null) {
      if (other.second != null) {
        return false;
      }
    }
    else if (!second.equals(other.second)) {
      return false;
    }
    return true;
  }

  public static <F, S> Pair<F, S> createPair(final F first, final S second)
  {
    return new Pair<F, S>(first, second);
  }
}