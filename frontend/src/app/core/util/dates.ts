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
