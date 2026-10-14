import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import type { Observable } from 'rxjs';
import type {
  AssignSkillToUserRequest,
  CreateSkillCategoryRequest,
  CreateSkillRequest,
  CreateUserContactRequest,
  SkillAdminResponse,
  SkillCategoryAdminResponse,
  UnassignSkillFromUserRequest,
  UpdateSkillCategoryRequest,
  UpdateSkillRequest,
  UpdateUserContactRequest,
  UpdateUserSummaryRequest,
  UserContactAdminResponse,
  UserEducationPathAdminResponse,
  UserExperiencePathAdminResponse,
  UserSkillResponse,
  UserSummaryAdminResponse,
} from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import type { EducationPathPayload, ExperiencePathPayload } from './admin.models';

const OPTIONS = { withCredentials: true } as const;

@Injectable({ providedIn: 'root' })
export class AdminApiService {
  private readonly http = inject(HttpClient);

  getSummary(): Observable<UserSummaryAdminResponse> {
    return this.http.get<UserSummaryAdminResponse>(`${API_BASE_PATH}/admin/profile/summary`, OPTIONS);
  }

  createSummary(request: UpdateUserSummaryRequest): Observable<UserSummaryAdminResponse> {
    return this.http.post<UserSummaryAdminResponse>(`${API_BASE_PATH}/admin/profile/summary`, request, OPTIONS);
  }

  updateSummary(request: UpdateUserSummaryRequest): Observable<UserSummaryAdminResponse> {
    return this.http.patch<UserSummaryAdminResponse>(`${API_BASE_PATH}/admin/profile/summary`, request, OPTIONS);
  }

  getCategories(): Observable<SkillCategoryAdminResponse[]> {
    return this.http.get<SkillCategoryAdminResponse[]>(`${API_BASE_PATH}/admin/skill-categories`, OPTIONS);
  }

  createCategory(request: CreateSkillCategoryRequest): Observable<SkillCategoryAdminResponse> {
    return this.http.post<SkillCategoryAdminResponse>(`${API_BASE_PATH}/admin/skill-categories`, request, OPTIONS);
  }

  updateCategory(id: number, request: UpdateSkillCategoryRequest): Observable<SkillCategoryAdminResponse> {
    return this.http.patch<SkillCategoryAdminResponse>(`${API_BASE_PATH}/admin/skill-categories/${id}`, request, OPTIONS);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${API_BASE_PATH}/admin/skill-categories/${id}`, OPTIONS);
  }

  createSkill(request: CreateSkillRequest): Observable<SkillAdminResponse> {
    return this.http.post<SkillAdminResponse>(`${API_BASE_PATH}/admin/skills`, request, OPTIONS);
  }

  updateSkill(id: number, request: UpdateSkillRequest): Observable<SkillAdminResponse> {
    return this.http.patch<SkillAdminResponse>(`${API_BASE_PATH}/admin/skills/${id}`, request, OPTIONS);
  }

  getUserSkills(): Observable<UserSkillResponse[]> {
    return this.http.get<UserSkillResponse[]>(`${API_BASE_PATH}/admin/profile/skills`, OPTIONS);
  }

  assignSkillToUser(request: AssignSkillToUserRequest): Observable<void> {
    return this.http.post<void>(`${API_BASE_PATH}/admin/profile/skills`, request, OPTIONS);
  }

  unassignSkillFromUser(request: UnassignSkillFromUserRequest): Observable<void> {
    return this.http.delete<void>(`${API_BASE_PATH}/admin/profile/skills`, { ...OPTIONS, body: request });
  }

  getContacts(): Observable<UserContactAdminResponse[]> {
    return this.http.get<UserContactAdminResponse[]>(`${API_BASE_PATH}/admin/profile/contacts`, OPTIONS);
  }

  createContact(request: CreateUserContactRequest): Observable<UserContactAdminResponse> {
    return this.http.post<UserContactAdminResponse>(`${API_BASE_PATH}/admin/profile/contacts`, request, OPTIONS);
  }

  updateContact(id: number, request: UpdateUserContactRequest): Observable<UserContactAdminResponse> {
    return this.http.patch<UserContactAdminResponse>(`${API_BASE_PATH}/admin/profile/contacts/${id}`, request, OPTIONS);
  }

  deleteContact(id: number): Observable<void> {
    return this.http.delete<void>(`${API_BASE_PATH}/admin/profile/contacts/${id}`, OPTIONS);
  }

  getEducation(): Observable<UserEducationPathAdminResponse[]> {
    return this.http.get<UserEducationPathAdminResponse[]>(`${API_BASE_PATH}/admin/profile/education-paths`, OPTIONS);
  }

  createEducation(request: EducationPathPayload): Observable<UserEducationPathAdminResponse> {
    return this.http.post<UserEducationPathAdminResponse>(`${API_BASE_PATH}/admin/profile/education-paths`, request, OPTIONS);
  }

  updateEducation(id: string, request: EducationPathPayload): Observable<UserEducationPathAdminResponse> {
    return this.http.patch<UserEducationPathAdminResponse>(`${API_BASE_PATH}/admin/profile/education-paths/${id}`, request, OPTIONS);
  }

  deleteEducation(id: string): Observable<void> {
    return this.http.delete<void>(`${API_BASE_PATH}/admin/profile/education-paths/${id}`, OPTIONS);
  }

  getExperience(): Observable<UserExperiencePathAdminResponse[]> {
    return this.http.get<UserExperiencePathAdminResponse[]>(`${API_BASE_PATH}/admin/profile/experience-paths`, OPTIONS);
  }

  createExperience(request: ExperiencePathPayload): Observable<UserExperiencePathAdminResponse> {
    return this.http.post<UserExperiencePathAdminResponse>(`${API_BASE_PATH}/admin/profile/experience-paths`, request, OPTIONS);
  }

  updateExperience(id: string, request: ExperiencePathPayload): Observable<UserExperiencePathAdminResponse> {
    return this.http.patch<UserExperiencePathAdminResponse>(`${API_BASE_PATH}/admin/profile/experience-paths/${id}`, request, OPTIONS);
  }

  deleteExperience(id: string): Observable<void> {
    return this.http.delete<void>(`${API_BASE_PATH}/admin/profile/experience-paths/${id}`, OPTIONS);
  }
}