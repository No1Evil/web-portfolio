import { computed, type ResourceStatus, type Signal } from '@angular/core';
import type { AsyncState } from './async-state';

/**
 * Maps an Angular resource's status/value pair onto the app's AsyncState vocabulary.
 * Errors and missing values become 'empty'; everything else while pending is 'loading'.
 */
export function resourceState<T>(
  status: Signal<ResourceStatus>,
  value: Signal<T | undefined>,
  isEmpty: (value: T) => boolean = () => false,
): Signal<AsyncState<T>> {
  return computed(() => {
    const current = status();
    if (current === 'error') return { status: 'empty' };
    if (current === 'resolved' || current === 'local') {
      const resolved = value();
      if (resolved === undefined || isEmpty(resolved)) return { status: 'empty' };
      return { status: 'resolved', value: resolved };
    }
    return { status: 'loading' };
  });
}