package de.upb.soot.callgraph.typehierarchy;

import de.upb.soot.core.model.SootClass;

/** For internal use only. */
public interface MutableTypeHierarchy extends TypeHierarchy {

  /**
   * Adds the type to the hierarchy, updating all structures to reflect its presence as if it had
   * been a member of the hierarchy from the beginning.
   *
   * <p>For internal use only.
   */
  void addType(SootClass sootClass);
}
