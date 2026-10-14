/** The load state of a remote resource: waiting, unavailable, or loaded. */
export type AsyncState<T> =
  | { status: 'loading' }
  | { status: 'empty' }
  | { status: 'resolved'; value: T };

/** Returns the resolved value of an async state, or `undefined` while loading or empty. */
export function unwrap<T>(state: AsyncState<T>): T | undefined {
  return state.status === 'resolved' ? state.value : undefined;
}