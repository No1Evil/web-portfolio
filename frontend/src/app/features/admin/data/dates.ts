/** Converts a date value to the value used by a native `<input type="date">` (YYYY-MM-DD). */
export function toDateInput(value: string | Date | null | undefined): string {
  if (!value) return '';
  const date = value instanceof Date ? value : new Date(value);
  if (Number.isNaN(date.getTime())) return '';
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

/** Converts a date input value to an ISO-8601 offset date-time the backend expects. */
export function toIso(value: string): string | undefined {
  return value ? `${value}T00:00:00Z` : undefined;
}

/** Formats start/end dates as a short period, e.g. "2016 — 2019" or "2021 — Present". */
export function formatPeriod(
  start: string | Date | null | undefined,
  end: string | Date | null | undefined,
  present: boolean | null | undefined,
  presentLabel: string,
): string {
  const startYear = start ? yearOf(start) : '';
  if (present) return `${startYear} — ${presentLabel}`;
  const endYear = end ? yearOf(end) : '';
  if (startYear && endYear) return `${startYear} — ${endYear}`;
  return startYear || endYear || '';
}

function yearOf(value: string | Date): string {
  const date = value instanceof Date ? value : new Date(value);
  return Number.isNaN(date.getTime()) ? '' : String(date.getFullYear());
}